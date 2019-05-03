package konnov.commr.vk.geographicalquiz.util;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.List;

import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.pojo.TranslationIdentifier;

public class Misc {
    public static SparseArray<Question> questionListToSparseArray(List<Question> questions){
        SparseArray<Question> sparseArray = new SparseArray<>();
        for(Question question : questions) {
            sparseArray.put(question.getQuestionId(), question);
        }
        return sparseArray;
    }

    public static HashMap<TranslationIdentifier, Translation> translationListToHashMap(List<Translation> translations){
        HashMap<TranslationIdentifier, Translation> translationMap = new HashMap<>();
        for(Translation translation : translations) {
            translationMap.put(new TranslationIdentifier(translation.getQuestionId(), translation.getLanguageId()),
                    translation);
        }
        return translationMap;
    }

    @Deprecated
    public static SparseArray<Translation> translationListToSparseArray(List<Translation> translations){
        SparseArray<Translation> sparseArray = new SparseArray<>();
        for(Translation translation : translations) {
            sparseArray.put(translation.getQuestionId(), translation);
        }
        return sparseArray;
    }

}
