package konnov.commr.vk.geographicalquiz;

import android.content.Context;

import androidx.annotation.NonNull;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsRepository;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;
import konnov.commr.vk.geographicalquiz.data.source.local.BitmapStorage;
import konnov.commr.vk.geographicalquiz.data.source.local.QuestionsDatabase;
import konnov.commr.vk.geographicalquiz.data.source.local.QuestionsLocalDataSource;
import konnov.commr.vk.geographicalquiz.data.source.remote.QuestionsRemoteDataSource;
import konnov.commr.vk.geographicalquiz.util.AppExecutors;


/**
 * Enables injection of mock implementations for
 * {@link QuestionsDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {
    public static QuestionsRepository provideQuestionsRepository(@NonNull Context context) {
        AppExecutors appExecutors = new AppExecutors();
        QuestionsDatabase database = QuestionsDatabase.getInstance(context);
        QuestionsLocalDataSource localDataSource = QuestionsLocalDataSource.getInstance(appExecutors,
                database.questionsDao(), database.translationsDao());
        BitmapStorage bitmapStorage = BitmapStorage.getInstance(context, appExecutors);
        QuestionsRemoteDataSource remoteDataSource = QuestionsRemoteDataSource.getInstance(appExecutors);

        return QuestionsRepository.getInstance(
                remoteDataSource,
                localDataSource,
                bitmapStorage);
    }
}
