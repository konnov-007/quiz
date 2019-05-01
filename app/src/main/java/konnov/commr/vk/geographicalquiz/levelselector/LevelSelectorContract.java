package konnov.commr.vk.geographicalquiz.levelselector;

import konnov.commr.vk.geographicalquiz.BasePresenter;
import konnov.commr.vk.geographicalquiz.BaseView;

public interface LevelSelectorContract {

    interface View extends BaseView<Presenter> {}

    interface Presenter extends BasePresenter<View> {}
}
