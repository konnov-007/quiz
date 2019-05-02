package konnov.commr.vk.geographicalquiz.data.source.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;

import konnov.commr.vk.geographicalquiz.data.pojo.Image;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;
import konnov.commr.vk.geographicalquiz.data.Entries;
import konnov.commr.vk.geographicalquiz.util.AppExecutors;

public class QuestionsRemoteDataSource implements QuestionsDataSource {

    private static QuestionsRemoteDataSource INSTANCE;

    private DatabaseReference mRootRefQuestions = FirebaseDatabase.getInstance().getReference("questions");

    private DatabaseReference mRootRefTranslations = FirebaseDatabase.getInstance().getReference("translations");

    private GenericTypeIndicator<ArrayList<Object>> objectsGTypeInd = new GenericTypeIndicator<ArrayList<Object>>() {};

    private AppExecutors mAppExecutors;

    private QuestionsRemoteDataSource(AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
    }

    public static QuestionsRemoteDataSource getInstance(AppExecutors appExecutors) {
        if (INSTANCE == null) {
            INSTANCE = new QuestionsRemoteDataSource(appExecutors);
        }
        return INSTANCE;
    }


    private int imagesToDownload;
    @Override
    public void getImages(@NonNull final SparseArray<Translation> translations, final ImagesReceivedCallback callback) {
        Runnable fetchingImagesRunnable = new Runnable() {
            @Override
            public void run() {
                final ArrayList<Image> resultImages = new ArrayList<>();
                final ArrayList<Translation> translationsWithImages = getTranslationsWithImages(translations);
                imagesToDownload = translationsWithImages.size();
                if(imagesToDownload == 0) {
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onImagesLoaded(resultImages);
                        }
                    });
                }
                for (final Translation translation: translationsWithImages) {
                    final int questionId = translation.getQuestionId();
                    final String imageURL = translation.getImgLocation();
                    StorageReference mImageRef =
                            FirebaseStorage.getInstance().getReference(imageURL);
                    final long ONE_MEGABYTE = 1024 * 1024;
                    mImageRef.getBytes(ONE_MEGABYTE)
                            .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    String filename = imageURL.split("/")[1];
                                    Image image = new Image(questionId, bm, filename);
                                    translation.setImgLocation(filename);
                                    resultImages.add(image);
                                    imagesToDownload--;
                                    if(imagesToDownload == 0) {
                                        mAppExecutors.mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                callback.onImagesLoaded(resultImages);
                                            }
                                        });
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Crashlytics.logException(exception);
                            callback.onDataNotAvailable();
                        }
                    });
                }
            }
        };
        mAppExecutors.networkIO().execute(fetchingImagesRunnable);
    }

    private ArrayList<Translation> getTranslationsWithImages(SparseArray<Translation> translations) {
        ArrayList<Translation> translationsWithImages = new ArrayList<>();
        for(int i = 0; i < translations.size(); i++) {
            Translation translation = translations.valueAt(i);
            if(translation.getImgLocation() != null && !translation.getImgLocation().isEmpty()) {
                translationsWithImages.add(translation);
            }
        }
        return translationsWithImages;
    }

    @Override
    public void getQuestions(@NonNull LoadQuestionsCallback callback) {
        getQuestionFromFirebase(callback);
    }

    @Override
    public void saveQuestions(@NonNull SparseArray<Question> questions) {
        ///there is no save question from user functionality
    }

    @Override
    public void saveTranslation(@NonNull SparseArray<Translation> translations) {
        ///there is no save question from user functionality
    }

    @Override
    public void refreshQuestions() {
    // Not required because the {@link QuestionsRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllQuestions() {
        //we only delete questions from the local DB
    }

    private void getQuestionFromFirebase(final LoadQuestionsCallback callback){
        mRootRefQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Runnable creatingPOJOsRunnable = new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Object> objectsList = dataSnapshot.getValue(objectsGTypeInd);
                        final SparseArray<Question> questions = generateQuestionsMap(objectsList);
                        mAppExecutors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                callback.onQuestionsLoaded(questions);
                            }
                        });
                    }
                };
                mAppExecutors.networkIO().execute(creatingPOJOsRunnable);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Crashlytics.logException(databaseError.toException());
                callback.onDataNotAvailable();
            }
        });

        mRootRefTranslations.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Runnable creatingPOJOsRunnable = new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Object> objectsList = dataSnapshot.getValue(objectsGTypeInd);
                        final SparseArray<Translation> translations = generateTranslationsMap(objectsList);
                        mAppExecutors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                callback.onTranslationsLoaded(translations);
                            }
                        });
                    }
                };
                mAppExecutors.networkIO().execute(creatingPOJOsRunnable);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Crashlytics.logException(databaseError.toException());
                callback.onDataNotAvailable();
            }
        });
    }

    private SparseArray<Question> generateQuestionsMap(ArrayList<Object> objectList) {
        SparseArray<Question> questionsMap = new SparseArray<>();
        for(Object object : objectList) {
            HashMap <String, Long> questionSet = (HashMap<String, Long>) object;
            long questionId = questionSet.get(Entries.QUESTION_ID);
            long questionDifficulty = questionSet.get(Entries.DIFFICULTY);
            long questionRightAnswer = questionSet.get(Entries.RIGHT_ANSWER);
            Question question = new Question((int) questionId, (int) questionDifficulty, (int) questionRightAnswer);
            questionsMap.put((int) questionId, question);
        }
        return questionsMap;
    }

    private SparseArray<Translation> generateTranslationsMap(ArrayList<Object> objectList) {
        SparseArray<Translation> questionsMap = new SparseArray<>();
        for(Object object : objectList) {
            HashMap <String, Object> questionSet = (HashMap<String, Object>) object;
            long questionId = (Long) questionSet.get(Entries.QUESTION_ID);
            String languageId = (String) questionSet.get(Entries.LANGUAGE_ID);
            String title = (String) questionSet.get(Entries.TITLE);
            String imageServerUrl = (String) questionSet.get(Entries.IMG);
            String answerOne = (String) questionSet.get(Entries.ANSWER_1);
            String answerTwo = (String) questionSet.get(Entries.ANSWER_2);
            String answerThree = (String) questionSet.get(Entries.ANSWER_3);
            String answerFour = (String) questionSet.get(Entries.ANSWER_4);
            String wrongAnswerComment = (String) questionSet.get(Entries.WRONG_ANSWER_COMMENT);
            Translation translation = new Translation((int) questionId, languageId, title,
                    imageServerUrl, answerOne, answerTwo, answerThree, answerFour, wrongAnswerComment);
            questionsMap.put((int) questionId, translation);
        }
        return questionsMap;
    }
}
