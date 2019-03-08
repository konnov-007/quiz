package konnov.commr.vk.geographicalquiz.interfaces;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.objects.Question;
import konnov.commr.vk.geographicalquiz.objects.Translation;

public interface WebService {
    void questionsReceived(HashMap<Integer, Question> questions);
    void translationsReceived(HashMap<Integer, Translation> translations);
}
