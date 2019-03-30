package konnov.commr.vk.geographicalquiz.data.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import konnov.commr.vk.geographicalquiz.data.Entries;

@Entity(tableName = Entries.QUESTION_TABLE)
public final class Question {

    @PrimaryKey
    @ColumnInfo(name = Entries.QUESTION_ID)
    private final int mQuestionId;

    @ColumnInfo(name = Entries.DIFFICULTY)
    private final int mDifficulty;

    @ColumnInfo(name = Entries.RIGHT_ANSWER)
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
        return mQuestionId + mDifficulty + mRightAnswer;
    }
}
