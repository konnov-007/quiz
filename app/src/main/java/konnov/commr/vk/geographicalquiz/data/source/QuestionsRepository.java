package konnov.commr.vk.geographicalquiz.data.source;

import android.graphics.Bitmap;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.data.pojo.Image;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.pojo.TranslationIdentifier;
import konnov.commr.vk.geographicalquiz.data.source.local.BitmapStorage;

public class QuestionsRepository implements QuestionsDataSource{

    private static QuestionsRepository INSTANCE = null;

    private final QuestionsDataSource mQuestionsRemoteDataSource;

    private final QuestionsDataSource mQuestionsLocalDataSource;

    private final BitmapStorage mBitmapStorage;

    SparseArray<Question> mCachedQuestions; //questionId : question map

    HashMap<TranslationIdentifier, Translation> mCachedTranslations; //questionId : translation map

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    private QuestionsRepository (@NonNull QuestionsDataSource questionsRemoteDataSource,
                                       @NonNull QuestionsDataSource questionsLocalDataSource,
                                        @NonNull BitmapStorage bitmapStorage) {
        mQuestionsRemoteDataSource = questionsRemoteDataSource;
        mQuestionsLocalDataSource = questionsLocalDataSource;
        mBitmapStorage = bitmapStorage;
    }


    public static QuestionsRepository getInstance (QuestionsDataSource questionsRemoteDataSource,
                                                   QuestionsDataSource questionsLocalDataSource,
                                                   BitmapStorage bitmapStorage) {
        if (INSTANCE == null) {
            INSTANCE = new QuestionsRepository(questionsRemoteDataSource, questionsLocalDataSource, bitmapStorage);
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getImages(@NonNull HashMap<TranslationIdentifier, Translation> translations, ImagesReceivedCallback callback) {
        //This method is only used in QuestionRemoteDataSource
    }

    public Bitmap getImage(String filename) {
        return mBitmapStorage.getImage(filename);
    }

    /**
     * Gets questions from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link LoadQuestionsCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getQuestions(@NonNull final LoadQuestionsCallback callback) {
        // Respond immediately with cache if available and not dirty
        if(mCachedQuestions != null && mCachedTranslations != null && !mCacheIsDirty) {
            callback.onQuestionsLoaded(mCachedQuestions);
            callback.onTranslationsLoaded(mCachedTranslations);
            return;
        }

        if(mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getQuestionsFromRemoteDataSource(callback);
        } else {
            mQuestionsLocalDataSource.getQuestions(new LoadQuestionsCallback() {
                @Override
                public void onQuestionsLoaded(SparseArray<Question> questions) {
                    refreshCacheQuestions(questions);
                    callback.onQuestionsLoaded(questions);
                }

                @Override
                public void onTranslationsLoaded(HashMap<TranslationIdentifier, Translation> translations) {
                    refreshCacheTranslations(translations);
                    callback.onTranslationsLoaded(translations);
                }

                @Override
                public void onDataNotAvailable() {
                    getQuestionsFromRemoteDataSource(callback);
                }
            });
        }
    }

    @Override
    public void saveQuestions(@NonNull SparseArray<Question> questions) {
        mQuestionsLocalDataSource.saveQuestions(questions);
    }

    @Override
    public void saveTranslation(@NonNull HashMap<TranslationIdentifier, Translation> translations) {
        mQuestionsLocalDataSource.saveTranslation(translations);
    }


    private void getQuestionsFromRemoteDataSource(@NonNull final LoadQuestionsCallback callback) {
        mQuestionsRemoteDataSource.getQuestions(new LoadQuestionsCallback() {
            @Override
            public void onQuestionsLoaded(SparseArray<Question> questions) {
                refreshCacheQuestions(questions);
                refreshQuestionsTable(questions);
                callback.onQuestionsLoaded(questions);
            }

            @Override
            public void onTranslationsLoaded(final HashMap<TranslationIdentifier, Translation> translations) {
                mQuestionsRemoteDataSource.getImages(translations, new ImagesReceivedCallback() {
                    @Override
                    public void onImagesLoaded(ArrayList<Image> images) {
                        mBitmapStorage.deleteAllImages();
                        mBitmapStorage.saveImages(images);
                        refreshCacheTranslations(translations);
                        refreshTranslationsTable(translations);
                        callback.onTranslationsLoaded(translations);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });

            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCacheQuestions(SparseArray<Question> questions) {
        if (mCachedQuestions == null) {
            mCachedQuestions = new SparseArray<>();
        }
        mCachedQuestions.clear();
        for (int i = 0; i < questions.size(); i++) {
            mCachedQuestions.put(questions.valueAt(i).getQuestionId(), questions.valueAt(i));
        }
        mCacheIsDirty = false;
    }

    private void refreshCacheTranslations(HashMap<TranslationIdentifier, Translation> translations) {
        if (mCachedTranslations == null) {
            mCachedTranslations = new HashMap<>();
        }
        mCachedTranslations.clear();
        mCachedTranslations = translations;
        mCacheIsDirty = false;
    }

    private void refreshQuestionsTable(SparseArray<Question> questions){
        mQuestionsLocalDataSource.deleteAllQuestions();
        mQuestionsLocalDataSource.saveQuestions(questions);
    }

    private void refreshTranslationsTable(HashMap<TranslationIdentifier, Translation> translations){
       mQuestionsLocalDataSource.saveTranslation(translations);
    }

    @Override
    public void refreshQuestions() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllQuestions() {
        mQuestionsLocalDataSource.deleteAllQuestions();
        if(mCachedQuestions == null) {
            mCachedQuestions = new SparseArray<>();
        }
        if(mCachedTranslations == null) {
            mCachedTranslations = new HashMap<>();
        }
    }
}
