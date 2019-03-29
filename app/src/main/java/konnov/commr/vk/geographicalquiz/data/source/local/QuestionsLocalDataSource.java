package konnov.commr.vk.geographicalquiz.data.source.local;

import androidx.annotation.NonNull;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;
import konnov.commr.vk.geographicalquiz.util.AppExecutors;

public class QuestionsLocalDataSource implements QuestionsDataSource {

    private static QuestionsLocalDataSource INSTANCE;

    private QuestionsLocalDataSource(){
        //TODO implement local data source
    }

    public static QuestionsLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                       @NonNull QuestionsDao tasksDao) {
        if(INSTANCE == null) {
            INSTANCE = new QuestionsLocalDataSource();
        }
        return INSTANCE;
    }

    //TODO make the class
    @Override
    public void getQuestions(@NonNull LoadQuestionsCallback callback) {

    }

    @Override
    public void refreshQuestions() {

    }
}
