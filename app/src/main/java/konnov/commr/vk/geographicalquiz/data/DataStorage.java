package konnov.commr.vk.geographicalquiz.data;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.data.source.local.LocalDatabase;
import konnov.commr.vk.geographicalquiz.interfaces.Interfaces;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;

public class DataStorage  implements WebService {
    private static DataStorage mInstance = null;
    private HashMap<Integer, Question> questions;
    private HashMap<Integer, Translation> translations;
    LocalDatabase localDatabase = LocalDatabase.getInstance(null);

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
        localDatabase.insertQuestions(questions);
    }

    @Override
    public void translationsReceived(HashMap<Integer, Translation> translations) {
        this.translations = translations;
        localDatabase.insertTranslations(translations);
        System.out.printf(localDatabase.getTranslations().toString());
    }
}
