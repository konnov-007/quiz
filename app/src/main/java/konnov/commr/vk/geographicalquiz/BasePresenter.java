package konnov.commr.vk.geographicalquiz;

public interface BasePresenter <T> {

    void takeView(T view);

    void dropView();

}
