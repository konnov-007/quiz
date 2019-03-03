package konnov.commr.vk.geographicalquiz.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public class Interfaces {
    private HashMap<String, DataStorageModel> dataStorageModelMap = new HashMap<>();

    private static Interfaces mInstance = null;

    public static Interfaces getInstance() {
        if (mInstance == null) {
            mInstance = new Interfaces();
        }
        return mInstance;
    }

    private Interfaces(){};

    public void subscribeDataStorageModel(String classname, DataStorageModel context){
        if(!dataStorageModelMap.containsKey(classname)) {
            dataStorageModelMap.put(classname, context);
        }
    }

    public void unscubscribeDataStorageModel(String classname){
        dataStorageModelMap.remove(classname);
    }

    public void reportQuestionsReceived(ArrayList<Object> questions){
        for(DataStorageModel dataStorageModel : dataStorageModelMap.values()) {
            dataStorageModel.questionsReceived(questions);
        }
    };

    public void reportTranslationsReceived(ArrayList<Object> translations){
        for(DataStorageModel dataStorageModel : dataStorageModelMap.values()) {
            dataStorageModel.translationsReceived(translations);
        }
    };
}
