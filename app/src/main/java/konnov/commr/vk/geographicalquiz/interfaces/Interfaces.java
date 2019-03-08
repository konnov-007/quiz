package konnov.commr.vk.geographicalquiz.interfaces;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.objects.Question;
import konnov.commr.vk.geographicalquiz.objects.Translation;

public class Interfaces {
    private HashMap<String, WebService> WebServiceRefMap = new HashMap<>();

    private static Interfaces mInstance = null;

    public static Interfaces getInstance() {
        if (mInstance == null) {
            mInstance = new Interfaces();
        }
        return mInstance;
    }

    private Interfaces(){};

    public void subscribeWebService(String classname, WebService context){
        if(!WebServiceRefMap.containsKey(classname)) {
            WebServiceRefMap.put(classname, context);
        }
    }

    public void unscubscribeWebService(String classname){
        WebServiceRefMap.remove(classname);
    }

    public void reportQuestionsReceived(HashMap<Integer, Question> questions){
        for(WebService webService : WebServiceRefMap.values()) {
            webService.questionsReceived(questions);
        }
    };

    public void reportTranslationsReceived(HashMap<Integer, Translation> translations){
        for(WebService webService : WebServiceRefMap.values()) {
            webService.translationsReceived(translations);
        }
    };
}
