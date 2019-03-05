package konnov.commr.vk.geographicalquiz;

import android.app.Application;

import konnov.commr.vk.geographicalquiz.data.DataStorage;
import konnov.commr.vk.geographicalquiz.data.UserRepository;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataStorage.getInstance();
        UserRepository.getInstance();
    }
}
