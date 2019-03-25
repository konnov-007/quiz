package konnov.commr.vk.geographicalquiz.data.pojo;

import com.google.common.base.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public final class Question {

    @PrimaryKey
    @ColumnInfo(name = "question_id")
    private final int mQuestionId;

    @ColumnInfo(name = "mDifficulty")
    private final int mDifficulty;

    @ColumnInfo(name = "right_answer")
    private final int mRightAnswer;

    public Question(int questionId, int difficulty, int rightAnswer) {
        mQuestionId = questionId;
        mDifficulty = difficulty;
        mRightAnswer = rightAnswer;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public int getDifficulty() {
        return mDifficulty;
    }


    public int getRightAnswer() {
        return mRightAnswer;
    }


    @Override
    public String toString() {
        return "question id: " + mQuestionId + ", " +
                " mDifficulty: " + mDifficulty + ", " +
                " right answer: " + mRightAnswer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Question question = (Question) obj;
        return mQuestionId == question.getQuestionId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mQuestionId, mDifficulty, mRightAnswer); //TODO recheck if it's right
    }
}
