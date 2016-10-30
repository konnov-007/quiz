package konnov.commr.vk.geographicalquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ilya on 04.10.2016.
 */
public class Questions extends AppCompatActivity{
    private static Context context;
    public Questions(Context c) {
        context = c;
    }

    //private static final String TAG = "Questions";

    public void textForQuestion(int questionNumber, TextView question_text, Button first_answer_button, Button second_answer_button, Button third_answer_button, Button fourth_answer_button){
        switch (questionNumber){
            case 2:
                question_text.setText(R.string.question_2);
                first_answer_button.setText(R.string.question_2_answer_1);
                second_answer_button.setText(R.string.question_2_answer_2);
                third_answer_button.setText(R.string.question_2_answer_3);
                fourth_answer_button.setText(R.string.question_2_answer_4);
                break;
            default:break;
        }
   }

    public int checkIfAnswerRight(int questionNumber, int questionAnswer){

        if(questionNumber == 1 && questionAnswer == 3){
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(questionNumber == 1){
            Toast.makeText(context, R.string.question_1_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if(questionNumber == 2 && questionAnswer == 2){
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(questionNumber == 2){
            Toast.makeText(context, R.string.question_2_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
    return -999;
    }

}
