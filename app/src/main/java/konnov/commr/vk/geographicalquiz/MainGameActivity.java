package konnov.commr.vk.geographicalquiz;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener{
    TextView question_text;
    Button first_answer_button;
    Button second_answer_button;
    Button third_answer_button;
    Button fourth_answer_button;
    Questions questions = new Questions(MainGameActivity.this);
    int question = 1;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);

        question_text = (TextView) findViewById(R.id.question_text);
        first_answer_button = (Button) findViewById(R.id.answer1);
        second_answer_button = (Button) findViewById(R.id.answer2);
        third_answer_button = (Button) findViewById(R.id.answer3);
        fourth_answer_button = (Button) findViewById(R.id.answer4);

        first_answer_button.setOnClickListener(this);
        second_answer_button.setOnClickListener(this);
        third_answer_button.setOnClickListener(this);
        fourth_answer_button.setOnClickListener(this);

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
        score += questions.checkIfAnswerRight(question, answerNumber);
        question++;
        questions.textForQuestion(question, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);
        if(question == 3){
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
                        startActivity(new Intent(MainGameActivity.this, MainActivity.class));
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
}

