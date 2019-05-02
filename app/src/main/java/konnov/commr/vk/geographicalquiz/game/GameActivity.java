package konnov.commr.vk.geographicalquiz.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import konnov.commr.vk.geographicalquiz.Injection;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.gamefinish.FinishGameActivity;

public class GameActivity extends AppCompatActivity implements GameContract.View, View.OnClickListener{

    private TextView question_text;

    private Button first_answer_button;

    private Button second_answer_button;

    private Button third_answer_button;

    private Button fourth_answer_button;

    private ImageView question_pic;

    private GamePresenter mPresenter;

    private final int DELAY_AFTER_WRONG_ANSWER = 3500;

    private final int DELAY_AFTER_RIGHT_ANSWER = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.dropView();
    }

    private void initUI(){
        question_text = (TextView) findViewById(R.id.question_text);
        first_answer_button = (Button) findViewById(R.id.answer1);
        second_answer_button = (Button) findViewById(R.id.answer2);
        third_answer_button = (Button) findViewById(R.id.answer3);
        fourth_answer_button = (Button) findViewById(R.id.answer4);
        question_pic = (ImageView) findViewById(R.id.question_pic);

        first_answer_button.setOnClickListener(this);
        second_answer_button.setOnClickListener(this);
        third_answer_button.setOnClickListener(this);
        fourth_answer_button.setOnClickListener(this);

        mPresenter = new GamePresenter(Injection.provideQuestionsRepository(this));

        Intent intent = getIntent();
        int difficultyLevel = intent.getIntExtra("difficultyLevel", 0);

        mPresenter.fetchQuestionForDifficulty(difficultyLevel);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.answer1:
                mPresenter.answerButtonClick(1);
                break;
            case R.id.answer2:
                mPresenter.answerButtonClick(2);
                break;
            case R.id.answer3:
                mPresenter.answerButtonClick(3);
                break;
            case R.id.answer4:
                mPresenter.answerButtonClick(4);
                break;
            default:break;
        }
    }


    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.back_to_main_menu_alert_title)
                .setMessage(R.string.back_to_main_menu_alert_message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void setNewQuestionText(Translation translation, Bitmap image) {
        question_text.setText(translation.getTitle());
        first_answer_button.setText(translation.getAnswerOne());
        second_answer_button.setText(translation.getAnswerTwo());
        third_answer_button.setText(translation.getAnswerThree());
        fourth_answer_button.setText(translation.getAnswerFour());
        question_pic.setImageBitmap(image);
    }

    @Override
    public void reportWrongAnswer(String rightAnswerExplanation, int wrongAnswer) {
        handleReply(DELAY_AFTER_WRONG_ANSWER, Color.RED, rightAnswerExplanation, wrongAnswer, Snackbar.LENGTH_LONG);
    }

    @Override
    public void reportRightAnswer(int rightAnswer) {
        handleReply(DELAY_AFTER_RIGHT_ANSWER, Color.GREEN, getString(R.string.right_answer), rightAnswer, Snackbar.LENGTH_SHORT);

    }

    private void handleReply(int delay, int buttonBackground, String feedbackText, int clickedButton, int snackbarLength){
        disableButtons();

        switch (clickedButton) {
            case 1: {
                first_answer_button.setBackgroundColor(buttonBackground);
                break;
            }
            case 2: {
                second_answer_button.setBackgroundColor(buttonBackground);
                break;
            }
            case 3: {
                third_answer_button.setBackgroundColor(buttonBackground);
                break;
            }
            case 4: {
                fourth_answer_button.setBackgroundColor(buttonBackground);
                break;
            }
        }
        showMessage(feedbackText, snackbarLength);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enableButtons();
                mPresenter.provideQuestionText();
            }
        }, delay);
    }

    @Override
    public void showGameResults(int score) {
        Intent intent = new Intent(GameActivity.this, FinishGameActivity.class);
        intent.putExtra("int_score", score);
        startActivity(intent);
    }

    @Override
    public void showLoadingError() {
        showMessage(getResources().getString(R.string.network_error), Snackbar.LENGTH_LONG);
    }

    private void showMessage(String message, int length) {
        Snackbar.make(findViewById(android.R.id.content), message, length).show();
    }

    private void disableButtons(){
        first_answer_button.setEnabled(false);
        second_answer_button.setEnabled(false);
        third_answer_button.setEnabled(false);
        fourth_answer_button.setEnabled(false);
    }

    private void enableButtons(){
        first_answer_button.setBackgroundResource(android.R.color.holo_blue_light);
        second_answer_button.setBackgroundResource(android.R.color.holo_blue_light);
        third_answer_button.setBackgroundResource(android.R.color.holo_blue_light);
        fourth_answer_button.setBackgroundResource(android.R.color.holo_blue_light);
        first_answer_button.setEnabled(true);
        second_answer_button.setEnabled(true);
        third_answer_button.setEnabled(true);
        fourth_answer_button.setEnabled(true);
    }
}

