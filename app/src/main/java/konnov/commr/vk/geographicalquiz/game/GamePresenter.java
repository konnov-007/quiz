package konnov.commr.vk.geographicalquiz.game;

import android.graphics.Bitmap;
import android.util.SparseArray;

import konnov.commr.vk.geographicalquiz.data.pojo.Question;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
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

    @Override
    public void fetchQuestionForDifficulty(final int difficulty) {
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
            public void onTranslationsLoaded(SparseArray<Translation> translations) {
                for(int i = 0; i < translations.size(); i++) {

                    int questionId = translations.valueAt(i).getQuestionId();

                    if(mQuestions.get(questionId) != null) {
                        mTranslations.put(questionId, translations.valueAt(i));
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.showLoadingError();
            }
        });
    }

    @Override
    public void answerButtonClick(int buttonNumber) {
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
            mView.showGameResults(mScore);
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
