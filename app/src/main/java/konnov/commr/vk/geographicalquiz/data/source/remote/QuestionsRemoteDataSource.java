package konnov.commr.vk.geographicalquiz.data.source.remote;

import android.util.SparseArray;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;
import konnov.commr.vk.geographicalquiz.data.Entries;

public class QuestionsRemoteDataSource implements QuestionsDataSource {

    private static QuestionsRemoteDataSource INSTANCE;

    private DatabaseReference mRootRefQuestions = FirebaseDatabase.getInstance().getReference("questions");

    private DatabaseReference mRootRefTranslations = FirebaseDatabase.getInstance().getReference("translations");

    private GenericTypeIndicator<ArrayList<Object>> objectsGTypeInd = new GenericTypeIndicator<ArrayList<Object>>() {};


    private QuestionsRemoteDataSource() {}

    public static QuestionsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QuestionsRemoteDataSource();
        }
        return INSTANCE;
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
//todo execute in a different process

    private void getQuestionFromFirebase(final LoadQuestionsCallback callback){
        mRootRefQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> objectsList = dataSnapshot.getValue(objectsGTypeInd);
                SparseArray<Question> questions = generateQuestionsMap(objectsList);
                callback.onQuestionsLoaded(questions);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                callback.onDataNotAvailable();
            }
        });

        mRootRefTranslations.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> objectsList = dataSnapshot.getValue(objectsGTypeInd);
                SparseArray<Translation> translations = generateTranslationsMap(objectsList);
                callback.onTranslationsLoaded(translations);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
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
            String img = (String) questionSet.get(Entries.IMG);
            String answerOne = (String) questionSet.get(Entries.ANSWER_1);
            String answerTwo = (String) questionSet.get(Entries.ANSWER_2);
            String answerThree = (String) questionSet.get(Entries.ANSWER_3);
            String answerFour = (String) questionSet.get(Entries.ANSWER_4);
            String wrongAnswerComment = (String) questionSet.get(Entries.WRONG_ANSWER_COMMENT);
            Translation translation = new Translation((int) questionId, languageId, title,
                    img, answerOne, answerTwo, answerThree, answerFour, wrongAnswerComment);
            questionsMap.put((int) questionId, translation);
        }
        return questionsMap;
    }
}
