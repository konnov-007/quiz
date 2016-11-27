package konnov.commr.vk.geographicalquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener{
    protected TextView question_text;
    protected Button first_answer_button;
    protected Button second_answer_button;
    protected Button third_answer_button;
    protected Button fourth_answer_button;
    protected ImageView question_pic;
    int question = 1;
    int score = 0;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);

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

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);

        if(level == 3) {
            question_text.setText(R.string.level_3_question_1);
            first_answer_button.setText(R.string.level_3_question_1_answer_1);
            second_answer_button.setText(R.string.level_3_question_1_answer_2);
            third_answer_button.setText(R.string.level_3_question_1_answer_3);
            fourth_answer_button.setText(R.string.level_3_question_1_answer_4);
        }
    }

    @Override
    public void onClick(View v) {
        //TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.answer1:
                onClickAction(1);
                break;
            case R.id.answer2:
                onClickAction(2);
                break;
            case R.id.answer3:
                onClickAction(3);
                break;
            case R.id.answer4:
                onClickAction(4);
                break;
            default:break;
        }
    }

    private void onClickAction(int answerNumber){
        final Questions questions = new Questions(MainGameActivity.this, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button, question_pic, level);
        int delay;
        if(questions.checkIfAnswerRight(question, answerNumber) == 1) {
            score++;
            delay = 2000;
        }
        else
            delay = 3500;
        first_answer_button.setEnabled(false);
        second_answer_button.setEnabled(false);
        third_answer_button.setEnabled(false);
        fourth_answer_button.setEnabled(false);
        question++;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                questions.textForQuestion(question);
                first_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                second_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                third_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                fourth_answer_button.setBackgroundResource(android.R.drawable.btn_default);
                first_answer_button.setEnabled(true);
                second_answer_button.setEnabled(true);
                third_answer_button.setEnabled(true);
                fourth_answer_button.setEnabled(true);
            }
        }, delay);
        if(question == 5){
            Intent intent = new Intent(MainGameActivity.this, FinishGameActivity.class);
            intent.putExtra("int_score", score);
            startActivity(intent);
            finish();
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
                        startActivity(new Intent(MainGameActivity.this, LevelSelector.class));
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
}

