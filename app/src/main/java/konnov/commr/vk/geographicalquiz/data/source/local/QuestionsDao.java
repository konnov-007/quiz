package konnov.commr.vk.geographicalquiz.data.source.local;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import konnov.commr.vk.geographicalquiz.data.Entries;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;

@Dao
public interface QuestionsDao {

    @Query("SELECT * FROM " + Entries.QUESTION_TABLE)
    List<Question> getQuestions();

    @Query("DELETE FROM " + Entries.QUESTION_TABLE)
    void deleteQuestions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestion(Question question);

}
