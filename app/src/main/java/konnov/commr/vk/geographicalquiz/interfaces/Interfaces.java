package konnov.commr.vk.geographicalquiz.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

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

    public void reportQuestionsReceived(ArrayList<Object> questions){
        for(WebService webService : WebServiceRefMap.values()) {
            webService.questionsReceived(questions);
        }
    };

    public void reportTranslationsReceived(ArrayList<Object> translations){
        for(WebService webService : WebServiceRefMap.values()) {
            webService.translationsReceived(translations);
        }
    };
}
