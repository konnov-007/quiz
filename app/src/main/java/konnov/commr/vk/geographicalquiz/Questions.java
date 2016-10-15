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
        TextView question_text;
        ImageView question_image;
        Button first_answer_button;
        Button second_answer_button;
        Button third_answer_button;
        Button fourth_answer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);
        question_text = (TextView) findViewById(R.id.question_text);
        question_image = (ImageView) findViewById(R.id.questionImageView);
        first_answer_button = (Button) findViewById(R.id.answer1);
        second_answer_button = (Button) findViewById(R.id.answer2);
        third_answer_button = (Button) findViewById(R.id.answer3);
        fourth_answer_button = (Button) findViewById(R.id.answer4);
    }


    void mainGameMethod(int questionNumber, int questionAnswer){
        setContentView(R.layout.main_game);
        if(questionNumber == 1 && questionAnswer == 0)
            firstQuestionOutput();
        if(questionNumber == 1 && questionAnswer != 0)
            firstQuestionAnswer(questionAnswer);

    }

    private void firstQuestionOutput(){
        question_text.setText(R.string.question_1);
        first_answer_button.setText(R.string.question_1_answer_1);
        second_answer_button.setText(R.string.question_1_answer_2);
        third_answer_button.setText(R.string.question_1_answer_3);
        fourth_answer_button.setText(R.string.question_1_answer_4);
    }

    private boolean firstQuestionAnswer(int questionAnswer){
        if(questionAnswer == 3){
            Toast.makeText(this, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this, R.string.question_1_answer_wrong, Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
