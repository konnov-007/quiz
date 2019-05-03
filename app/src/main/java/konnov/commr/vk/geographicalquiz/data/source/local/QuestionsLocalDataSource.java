package konnov.commr.vk.geographicalquiz.data.source.local;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;

import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.pojo.TranslationIdentifier;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;
import konnov.commr.vk.geographicalquiz.util.AppExecutors;
import konnov.commr.vk.geographicalquiz.util.Misc;

public class QuestionsLocalDataSource implements QuestionsDataSource {

    private static QuestionsLocalDataSource INSTANCE;

    private QuestionsDao mQuestionsDao;

    private TranslationsDao mTranslationsDao;

    private AppExecutors mAppExecutors;


    private QuestionsLocalDataSource(@NonNull AppExecutors appExecutors,
                                     @NonNull QuestionsDao questionsDao,
                                     @NonNull TranslationsDao translationsDao){
        mAppExecutors = appExecutors;
        mQuestionsDao = questionsDao;
        mTranslationsDao = translationsDao;
    }

    public static QuestionsLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                       @NonNull QuestionsDao questionsDao,
                                                       @NonNull TranslationsDao translationsDao) {
        if(INSTANCE == null) {
            INSTANCE = new QuestionsLocalDataSource(appExecutors, questionsDao, translationsDao);
        }
        return INSTANCE;
    }


    @Override
    public void getImages(@NonNull HashMap<TranslationIdentifier, Translation> translations, ImagesReceivedCallback callback) {
        //TODO
    }

    @Override
    public void getQuestions(@NonNull final LoadQuestionsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Translation> translationsList = mTranslationsDao.getTranslations();
                final List<Question> questionsList = mQuestionsDao.getQuestions();
                final SparseArray<Question> questionSparseArray = Misc.questionListToSparseArray(questionsList);
                final HashMap<TranslationIdentifier, Translation> translationsSparseArray = Misc.translationListToHashMap(translationsList);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(translationsList.size() != 0 && questionsList.size() != 0) {
                            callback.onQuestionsLoaded(questionSparseArray);
                            callback.onTranslationsLoaded(translationsSparseArray);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveQuestions(@NonNull final SparseArray<Question> questions) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < questions.size(); i++) {
                    mQuestionsDao.insertQuestion(questions.valueAt(i));
                }
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void saveTranslation(@NonNull final HashMap<TranslationIdentifier, Translation> translations) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                for(Translation translation: translations.values()) {
                    mTranslationsDao.insertTranslation(translation);
                }
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void refreshQuestions() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllQuestions() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mQuestionsDao.deleteQuestions();
                mTranslationsDao.deleteTranslations();
            }
        };

        mAppExecutors.diskIO().execute(deleteRunnable);
    }
}
