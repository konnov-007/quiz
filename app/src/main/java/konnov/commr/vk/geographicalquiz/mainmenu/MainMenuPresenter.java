package konnov.commr.vk.geographicalquiz.mainmenu;

import android.util.SparseArray;

import konnov.commr.vk.geographicalquiz.data.QuestionsRepository;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;

public class MainMenuPresenter implements MainMenuContract.Presenter {
    private MainMenuContract.View mView = null;
    private final QuestionsRepository mQuestionsRepository;

    public MainMenuPresenter(QuestionsRepository questionsRepository){
        mQuestionsRepository = questionsRepository;
    }

    //Fetching questions from server or from room db when the first activity is run
    @Override
    public void fetchQuestions() {
        mQuestionsRepository.getQuestions(new QuestionsDataSource.LoadQuestionsCallback() {
            @Override
            public void onQuestionsLoaded(SparseArray<Question> questions) {
                System.out.println("Questions received : " + questions);
            }

            @Override
            public void onTranslationsLoaded(SparseArray<Translation> translations) {
                System.out.println("Translations received : " + translations);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
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
