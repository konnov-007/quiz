package konnov.commr.vk.geographicalquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ilya on 04.10.2016.
 */
public class Questions extends AppCompatActivity{

    public void mainGameMethod(int questionNumber, int questionAnswer, int  score, TextView question_text, Button first_answer_button, Button second_answer_button, Button third_answer_button, Button fourth_answer_button){
        if(questionNumber == 1 && questionAnswer == 0)
            firstQuestionOutput(question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);
        else if(questionNumber == 1)
            firstQuestionAnswer(questionAnswer, score);
        else
            finish();
    }

    private void firstQuestionOutput(TextView question_text, Button first_answer_button, Button second_answer_button, Button third_answer_button, Button fourth_answer_button){
        question_text.setText(R.string.question_1);
        first_answer_button.setText(R.string.question_1_answer_1);
        second_answer_button.setText(R.string.question_1_answer_2);
        third_answer_button.setText(R.string.question_1_answer_3);
        fourth_answer_button.setText(R.string.question_1_answer_4);
    }

    private void firstQuestionAnswer(int questionAnswer, int  score){
        if(questionAnswer == 3){
            score++;
            Toast.makeText(this, R.string.right_answer+score, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, R.string.question_1_answer_wrong, Toast.LENGTH_SHORT).show();

        }

    }
}
