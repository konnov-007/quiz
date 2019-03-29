package konnov.commr.vk.geographicalquiz.data.source.local;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import konnov.commr.vk.geographicalquiz.data.pojo.Question;

@Dao
public interface QuestionsDao {

    @Query("SELECT * FROM questions")
    List<Question> getQuestions();

    @Query("DELETE FROM questions")
    void deleteQuestions();

    //TODO insert questions and translations query
}
