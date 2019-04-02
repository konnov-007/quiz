package konnov.commr.vk.geographicalquiz.levelselector;

public class LevelSelectorPresenter implements LevelSelectorContract.Presenter {

    private LevelSelectorContract.View mView;

    @Override
    public void takeView(LevelSelectorContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
