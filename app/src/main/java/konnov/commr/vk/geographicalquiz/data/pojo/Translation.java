package konnov.commr.vk.geographicalquiz.data.pojo;

import android.os.Build;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import konnov.commr.vk.geographicalquiz.data.Entries;

@Entity(tableName = Entries.TRANSLATION_TABLE)
public final class Translation {
    /**
     * The point of translation id is to save multiple translations for one question in a database
     * that have the same question id but different language id
     */
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "translation_id")
    private String translationId = UUID.randomUUID().toString();

    @ColumnInfo(name = Entries.QUESTION_ID)
    private int questionId;

    @NonNull
    @ColumnInfo(name = Entries.LANGUAGE_ID)
    private String languageId;

    @NonNull
    @ColumnInfo(name = Entries.TITLE)
    private String title;

    @Nullable
    @ColumnInfo(name = Entries.IMG)
    private String imgLocation;

    @NonNull
    @ColumnInfo(name = Entries.ANSWER_1)
    private String answerOne;

    @NonNull
    @ColumnInfo(name = Entries.ANSWER_2)
    private String answerTwo;

    @NonNull
    @ColumnInfo(name = Entries.ANSWER_3)
    private String answerThree;

    @NonNull
    @ColumnInfo(name = Entries.ANSWER_4)
    private String answerFour;

    @NonNull
    @ColumnInfo(name = Entries.WRONG_ANSWER_COMMENT)
    private String wrongAnswerComment;

    public Translation(int questionId, @NonNull String languageId,
                       @NonNull String title, @Nullable String imgLocation,
                       @NonNull String answerOne, @NonNull String answerTwo,
                       @NonNull String answerThree, @NonNull String answerFour,
                       @NonNull String wrongAnswerComment) {
        this.questionId = questionId;
        this.languageId = languageId;
        this.title = title;
        this.imgLocation = imgLocation;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.wrongAnswerComment = wrongAnswerComment;
    }

    public int getQuestionId() {
        return questionId;
    }

    @NonNull
    public String getLanguageId() {
        return languageId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getImgLocation() {
        return imgLocation;
    }

    @NonNull
    public String getAnswerOne() {
        return answerOne;
    }

    @NonNull
    public String getAnswerTwo() {
        return answerTwo;
    }

    @NonNull
    public String getAnswerThree() {
        return answerThree;
    }

    @NonNull
    public String getAnswerFour() {
        return answerFour;
    }

    @NonNull
    public String getWrongAnswerComment() {
        return wrongAnswerComment;
    }

    public void setImgLocation(@Nullable String imgLocation) {
        this.imgLocation = imgLocation;
    }


    public void setTranslationId(String translationId) {
        this.translationId = translationId;
    }

    public String getTranslationId() {
        return translationId;
    }

    @NonNull
    @Override
    public String toString() {
        return "questionId: " + questionId + ", " +
                "languageId: " + languageId + ", " +
                "title: " + title + ", " +
                "imgLocation: " + imgLocation + ", " +
                "answerOne: " + answerOne + ", " +
                "answerTwo: " + answerTwo + ", " +
                "answerThree: " + answerThree + ", " +
                "answerFour: " + answerFour + ", " +
                "wrongAnswerComment: " + wrongAnswerComment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return questionId + Objects.hash(languageId, title, imgLocation, answerOne,
                answerTwo, answerThree, answerFour);
    }
}
