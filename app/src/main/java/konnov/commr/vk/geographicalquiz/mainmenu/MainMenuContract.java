package konnov.commr.vk.geographicalquiz.mainmenu;

import konnov.commr.vk.geographicalquiz.BasePresenter;
import konnov.commr.vk.geographicalquiz.BaseView;

public interface MainMenuContract {

    interface View extends BaseView<Presenter> {
        void initUI();
        void showLoadingQuestionsError();
        void startNewGame();
        void startAbout();
    }

    interface Presenter extends BasePresenter<View> {
        void fetchQuestions();
    }
}
