package konnov.commr.vk.geographicalquiz.data;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.interfaces.Interfaces;
import konnov.commr.vk.geographicalquiz.interfaces.WebService;
import konnov.commr.vk.geographicalquiz.objects.Question;
import konnov.commr.vk.geographicalquiz.objects.Translation;

public class DataStorage  implements WebService {
    private static DataStorage mInstance = null;
    HashMap<Integer, Question> questions;
    HashMap<Integer, Translation> translations;

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
    public void questionsReceived(HashMap<Integer, Question> questions) {
        this.questions = questions;
    }

    @Override
    public void translationsReceived(HashMap<Integer, Translation> translations) {
        this.translations = translations;
    }
}
