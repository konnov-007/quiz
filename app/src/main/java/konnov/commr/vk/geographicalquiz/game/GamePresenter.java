package konnov.commr.vk.geographicalquiz.game;

import android.graphics.Bitmap;
import android.util.SparseArray;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.data.pojo.TranslationIdentifier;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsDataSource;
import konnov.commr.vk.geographicalquiz.data.source.QuestionsRepository;

public class GamePresenter implements GameContract.Presenter{

    private SparseArray<Question> mQuestions = new SparseArray<>(); //questions for selected difficulty

    private SparseArray<Translation> mTranslations = new SparseArray<>(); //translations for selected difficulty

    private GameContract.View mView = null;

    private QuestionsRepository mQuestionsRepository = null;

    private int mQuestionIndex = 0; //first mQuestionIndex

    private int mScore = 0;

    public GamePresenter(QuestionsRepository questionsRepository) {
        mQuestionsRepository = questionsRepository;
    }

    /**
     * Gets questions for selected difficulty and selected language
     * if selected language doesn't exist on the server then it's automatically set to english
     */
    @Override
    public void fetchQuestionForSession(final int difficulty, final String language) {
        mQuestionsRepository.getQuestions(new QuestionsDataSource.LoadQuestionsCallback() {
            @Override
            public void onQuestionsLoaded(SparseArray<Question> questions) {
                for(int i = 0; i < questions.size(); i++) {

                    if(questions.valueAt(i).getDifficulty() == difficulty) {
                        mQuestions.put(questions.valueAt(i).getQuestionId(), questions.valueAt(i));
                    }
                }
            }

            @Override
            public void onTranslationsLoaded(HashMap<TranslationIdentifier, Translation> translations) {
                for (TranslationIdentifier translationIdentifier: translations.keySet()) {
                    int questionId = translationIdentifier.getQuestionId();
                    if(mQuestions.get(questionId) != null) {
                        if(translations.get(new TranslationIdentifier(questionId, language)) != null) {
                            mTranslations.put(translationIdentifier.getQuestionId(),
                                    translations.get(new TranslationIdentifier(questionId, language)));
                        } else {
                            mTranslations.put(translationIdentifier.getQuestionId(),
                                    translations.get(new TranslationIdentifier(questionId, "en")));
                        }
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.showLoadingError();
            }
        });
    }

    /**
     *
     * @param buttonNumber - the number of the button that was clicked
     * There are four answer buttons, their numbers are as follows:
     *  [1] [2]
     *  [3] [4]
     */
    @Override
    public void answerButtonClick(int buttonNumber) {
        if(mQuestionIndex == mQuestions.size()) {
            return;
        }
        if(buttonNumber == mQuestions.valueAt(mQuestionIndex).getRightAnswer()) {
            mScore++;
            mView.reportRightAnswer(buttonNumber);
        } else {
            String answerComment = mTranslations.get(mQuestions.valueAt(mQuestionIndex).getQuestionId()).getWrongAnswerComment();
            mView.reportWrongAnswer(answerComment, buttonNumber);
        }
        mQuestionIndex++;
    }

    @Override
    public void provideQuestionText() {
        if(mQuestionIndex >= mQuestions.size()) {
            mView.showGameResults(mScore, mQuestions.size());
            return;
        }
        Translation questionText = mTranslations.get(mQuestions.valueAt(mQuestionIndex).getQuestionId());
        if(questionText.getImgLocation() != null) {
            Bitmap image = mQuestionsRepository.getImage(questionText.getImgLocation());
            if(image != null) {
                mView.setNewQuestionText(questionText, image);
            }
        } else {
            mView.setNewQuestionText(questionText, null);
        }
    }

    @Override
    public void takeView(GameContract.View view) {
        mView = view;
        provideQuestionText();
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
