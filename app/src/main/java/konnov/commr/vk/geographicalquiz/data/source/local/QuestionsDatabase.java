package konnov.commr.vk.geographicalquiz.data.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;

@Database(entities = {Question.class}, version = 1) //TODO add Translation.class
public abstract class QuestionsDatabase extends RoomDatabase {

    private static QuestionsDatabase INSTANCE;

    public abstract QuestionsDao questionsDao();

    private static final Object sLock = new Object();

    public static QuestionsDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        QuestionsDatabase.class, "questions.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
