package konnov.commr.vk.geographicalquiz.data.source;

import android.util.SparseArray;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import konnov.commr.vk.geographicalquiz.data.pojo.Image;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;

public interface QuestionsDataSource {

    interface LoadQuestionsCallback {

        void onQuestionsLoaded(SparseArray<Question> questions);

        void onTranslationsLoaded(SparseArray<Translation> translations);

        void onDataNotAvailable();
    }

    interface ImagesReceivedCallback {

        void onImagesLoaded(ArrayList<Image> images);

        void onDataNotAvailable();
    }

    void getImages(@NonNull SparseArray<Translation> translations, ImagesReceivedCallback callback);

    void getQuestions(@NonNull LoadQuestionsCallback callback);

    void saveQuestions(@NonNull SparseArray<Question> questions);

    void saveTranslation(@NonNull SparseArray<Translation> translations);

    void refreshQuestions();

    void deleteAllQuestions();

}
