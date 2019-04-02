package konnov.commr.vk.geographicalquiz.levelselector;

import konnov.commr.vk.geographicalquiz.BasePresenter;
import konnov.commr.vk.geographicalquiz.BaseView;

public interface LevelSelectorContract {

    interface View extends BaseView<Presenter> {

        void startQuestions(android.view.View view);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
