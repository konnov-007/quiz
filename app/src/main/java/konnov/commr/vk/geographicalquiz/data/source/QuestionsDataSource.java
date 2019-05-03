package konnov.commr.vk.geographicalquiz.data.source;

import android.util.SparseArray;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.data.pojo.Image;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.pojo.TranslationIdentifier;

public interface QuestionsDataSource {

    interface LoadQuestionsCallback {

        void onQuestionsLoaded(SparseArray<Question> questions);

        void onTranslationsLoaded(HashMap<TranslationIdentifier, Translation> translations);

        void onDataNotAvailable();
    }

    interface ImagesReceivedCallback {

        void onImagesLoaded(ArrayList<Image> images);

        void onDataNotAvailable();
    }

    void getImages(@NonNull HashMap<TranslationIdentifier, Translation> translations, ImagesReceivedCallback callback);

    void getQuestions(@NonNull LoadQuestionsCallback callback);

    void saveQuestions(@NonNull SparseArray<Question> questions);

    void saveTranslation(@NonNull HashMap<TranslationIdentifier, Translation> translations);

    void refreshQuestions();

    void deleteAllQuestions();

}
