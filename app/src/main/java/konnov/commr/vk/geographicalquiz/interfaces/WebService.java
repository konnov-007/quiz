package konnov.commr.vk.geographicalquiz.interfaces;

import java.util.ArrayList;

public interface WebService {
    void questionsReceived(ArrayList<Object> questions);
    void translationsReceived(ArrayList<Object> translations);
}
