package konnov.commr.vk.geographicalquiz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        if (v.getId() == R.id.answer1) {
            score += questions.checkIfAnswerRight(question, 1);
            question++;
            questions.textForQuestion(question, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);
            if(question == 3){
                Intent intent = new Intent(MainGameActivity.this, FinishGameActivity.class);
                intent.putExtra("int_score", score);
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.answer2) {
            score += questions.checkIfAnswerRight(question, 2);
            question++;
            questions.textForQuestion(question, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);
            if(question == 3){
                Intent intent = new Intent(MainGameActivity.this, FinishGameActivity.class);
                intent.putExtra("int_score", score);
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.answer3) {
            score += questions.checkIfAnswerRight(question, 3);
            question++;
            questions.textForQuestion(question, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);
            if(question == 3){
                Intent intent = new Intent(MainGameActivity.this, FinishGameActivity.class);
                intent.putExtra("int_score", score);
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.answer4) {
            score += questions.checkIfAnswerRight(question, 4);
            question++;
            questions.textForQuestion(question, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);
            if(question == 3){
                Intent intent = new Intent(MainGameActivity.this, FinishGameActivity.class);
                intent.putExtra("int_score", score);
                startActivity(intent);
            }
        }

    }
}

