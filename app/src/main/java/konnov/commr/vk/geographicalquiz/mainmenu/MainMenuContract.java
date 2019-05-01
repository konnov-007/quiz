package konnov.commr.vk.geographicalquiz.mainmenu;

import konnov.commr.vk.geographicalquiz.BasePresenter;
import konnov.commr.vk.geographicalquiz.BaseView;

public interface MainMenuContract {

    interface View extends BaseView<Presenter> {

        void showLoadingQuestionsError();

        void showUpdatingQuestionsSuccess();

        void showLoadingQuestionsStarted();
    }

    interface Presenter extends BasePresenter<View> {

        void fetchQuestions();

        void updateQuestions();
    }
}
