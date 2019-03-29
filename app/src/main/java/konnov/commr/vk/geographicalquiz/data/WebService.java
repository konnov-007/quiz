package konnov.commr.vk.geographicalquiz.data;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;

@Deprecated
public interface WebService {
    void questionsReceived(HashMap<Integer, Question> questions);
    void translationsReceived(HashMap<Integer, Translation> translations);
}
