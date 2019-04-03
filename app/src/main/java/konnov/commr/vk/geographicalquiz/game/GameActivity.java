package konnov.commr.vk.geographicalquiz.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import konnov.commr.vk.geographicalquiz.Injection;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;
import konnov.commr.vk.geographicalquiz.gamefinish.FinishGameActivity;
import konnov.commr.vk.geographicalquiz.levelselector.LevelSelectorActivity;

public class GameActivity extends AppCompatActivity implements GameContract.View, View.OnClickListener{

    private TextView question_text;

    private Button first_answer_button;

    private Button second_answer_button;

    private Button third_answer_button;

    private Button fourth_answer_button;

    private ImageView question_pic; //TODO implement displaying picture

    private GamePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);
        initUI();
        mPresenter.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
                        startActivity(new Intent(GameActivity.this, LevelSelectorActivity.class));
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            if (hasFocus) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    @Override
    public void setNewQuestionText(Translation translation) { //TODO add pic
        question_text.setText(translation.getTitle());
        first_answer_button.setText(translation.getAnswerOne());
        second_answer_button.setText(translation.getAnswerTwo());
        third_answer_button.setText(translation.getAnswerThree());
        fourth_answer_button.setText(translation.getAnswerFour());
        if(translation.getImgLocation() != null) {
            System.out.println("image should be inserted");
        }
    }

    @Override
    public void reportWrongAnswer(String rightAnswerExplanation, int wrongAnswer) { //TODO
        handleReply(3500, Color.RED, rightAnswerExplanation, wrongAnswer, Toast.LENGTH_LONG);
    }

    @Override
    public void reportRightAnswer(int rightAnswer) {
        handleReply(2000, Color.GREEN, getString(R.string.right_answer), rightAnswer, Toast.LENGTH_SHORT);

    }

    private void handleReply(int delay, int buttonBackground, String feedbackText, int clickedButton, int toastLength){
        first_answer_button.setEnabled(false);
        second_answer_button.setEnabled(false);
        third_answer_button.setEnabled(false);
        fourth_answer_button.setEnabled(false);
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
        Toast.makeText(this, feedbackText, toastLength).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                first_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                second_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                third_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                fourth_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                first_answer_button.setEnabled(true);
                second_answer_button.setEnabled(true);
                third_answer_button.setEnabled(true);
                fourth_answer_button.setEnabled(true);
                mPresenter.provideQuestionText();
            }
        }, delay);
    }

    @Override
    public void showGameResults(int score) {
        Intent intent = new Intent(GameActivity.this, FinishGameActivity.class);
        intent.putExtra("int_score", score);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoadingError() {
        //todo show error
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.dropView();
    }
}

