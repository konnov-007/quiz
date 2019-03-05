package konnov.commr.vk.geographicalquiz.data;

import java.util.ArrayList;

import konnov.commr.vk.geographicalquiz.interfaces.Interfaces;
import konnov.commr.vk.geographicalquiz.interfaces.WebService;

public class DataStorage  implements WebService {
    private static DataStorage mInstance = null;

    public static DataStorage getInstance() {
        if (mInstance == null) {
            mInstance = new DataStorage();
        }
        return mInstance;
    }

    private DataStorage(){
        Interfaces interfaces = Interfaces.getInstance();
        interfaces.subscribeWebService(getClass().getName(), this);
    }

    @Override
    public void questionsReceived(ArrayList<Object> questions) {
        System.out.println(questions);
    }

    @Override
    public void translationsReceived(ArrayList<Object> translations) {
        System.out.println(translations);
    }
}
