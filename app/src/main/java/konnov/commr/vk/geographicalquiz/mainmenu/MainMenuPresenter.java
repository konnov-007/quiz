package konnov.commr.vk.geographicalquiz.mainmenu;

import android.util.Log;
import android.util.SparseArray;

import com.crashlytics.android.Crashlytics;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.data.pojo.TranslationIdentifier;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsRepository;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;

public class MainMenuPresenter implements MainMenuContract.Presenter {
    private MainMenuContract.View mView = null;
    private final QuestionsRepository mQuestionsRepository;

    public MainMenuPresenter(QuestionsRepository questionsRepository){
        mQuestionsRepository = questionsRepository;
    }

    /**
    Fetching questions from server or from room db when the first activity is run
     This needs to be done in the first activity because if the user runs the app for the first time his db might not
     be initialized and it could take some time to fetch it from server so we're doing it in advance
     */
    @Override
    public void fetchQuestions() {
        if(mView != null) {
            mView.showLoadingQuestionsStarted();
        }

        mQuestionsRepository.getQuestions(new QuestionsDataSource.LoadQuestionsCallback() {
            @Override
            public void onQuestionsLoaded(SparseArray<Question> questions) {
                Crashlytics.log(Log.DEBUG, "MainMenuPresenter","Questions received, size: " + questions.size() + ", data: "  + questions);
            }

            @Override
            public void onTranslationsLoaded(HashMap<TranslationIdentifier, Translation> translations) {
                Crashlytics.log(Log.DEBUG, "MainMenuPresenter", "Translations received, size: " + translations.size() + ", data: " + translations);
                if(mView != null) {
                    mView.showUpdatingQuestionsSuccess();
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(mView != null) {
                    mView.showLoadingQuestionsError();
                }
            }
        });
    }

    @Override
    public void updateQuestions() {
        mQuestionsRepository.refreshQuestions();
        fetchQuestions();
    }

    @Override
    public void takeView(MainMenuContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
