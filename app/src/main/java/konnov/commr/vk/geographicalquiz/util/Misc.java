package konnov.commr.vk.geographicalquiz.util;

import android.util.SparseArray;

import java.util.List;

import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;

public class Misc {
    public static SparseArray<Question> questionListToSparseArray(List<Question> questions){
        SparseArray<Question> sparseArray = new SparseArray<>();
        for(Question question : questions) {
            sparseArray.put(question.getQuestionId(), question);
        }
        return sparseArray;
    }

    public static SparseArray<Translation> translationListToSparseArray(List<Translation> translations){
        SparseArray<Translation> sparseArray = new SparseArray<>();
        for(Translation translation : translations) {
            sparseArray.put(translation.getQuestionId(), translation);
        }
        return sparseArray;
    }

}
