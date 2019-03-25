package konnov.commr.vk.geographicalquiz.mainmenu;

public class MainMenuPresenter implements MainMenuContract.Presenter {
    private MainMenuContract.View mView = null;

    @Override
    public void fetchQuestions() {

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
