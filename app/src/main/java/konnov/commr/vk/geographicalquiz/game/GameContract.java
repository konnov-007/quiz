package konnov.commr.vk.geographicalquiz.game;

import android.graphics.Bitmap;

import konnov.commr.vk.geographicalquiz.BasePresenter;
import konnov.commr.vk.geographicalquiz.BaseView;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;

public interface GameContract {

    interface View extends BaseView<Presenter>{

        void setNewQuestionText(Translation translation, Bitmap image);

        void reportWrongAnswer(String rightAnswerExplanation, int wrongAnswer);

        void reportRightAnswer(int rightAnswer);

        void showGameResults(int score);

        void showLoadingError();
    }

    interface Presenter extends BasePresenter<View>{

        void fetchQuestionForSession(int difficulty, String language);

        void answerButtonClick(int buttonNumber);

        void provideQuestionText();
    }

}
