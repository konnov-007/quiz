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

    private static final String TAG = "Questions";

    public void mainGameMethod(int questionNumber, int questionAnswer, int  score){
        if(questionNumber == 1) {
            Log.v(TAG, "questionNumber == 1");
            checkIfAnswerRight(questionNumber, questionAnswer, score);
            Log.v(TAG, "checkIfAnswerRight(questionNumber, questionAnswer, score);");
        }
        if(questionNumber == 2) {
            Log.v(TAG, "questionNumber == 2");
            //textForQuestion(questionNumber, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);
            //Log.v(TAG, "textForQuestion(questionNumber, question_text, first_answer_button, second_answer_button, third_answer_button, fourth_answer_button);");
            checkIfAnswerRight(questionNumber, questionAnswer, score);
            Log.v(TAG, "checkIfAnswerRight(questionNumber, questionAnswer, score);");
        }
    }

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

    public void checkIfAnswerRight(int questionNumber, int questionAnswer, int  score){
        Log.v(TAG, "public void checkIfAnswerRight(int questionNumber, int questionAnswer, int  score){");
        if(questionNumber == 1 && questionAnswer == 3){
            Log.v(TAG, "if(questionNumber == 1 && questionAnswer == 3){");
            score++;
            Log.v(TAG, "score++");
            Toast.makeText(context, R.string.right_answer+score, Toast.LENGTH_LONG).show();
            Log.v(TAG, " Toast.makeText(this, R.string.right_answer+score, Toast.LENGTH_SHORT).show();");
        }
        else if(questionNumber == 1){
//            Log.v(TAG,"else if(questionNumber == 1){");
//            Thread toastThread = new Thread() {
//                public void run() {
//                    Toast toast = Toast.makeText(getApplicationContext(), R.string.question_1_answer_wrong, Toast.LENGTH_LONG);
//                    Log.v(TAG,"Toast toast = Toast.makeText(getApplicationContext(), R.string.question_1_answer_wrong, Toast.LENGTH_LONG);");
//                    toast.show();
//                    Log.v(TAG,"toast.show();");
//                }
//            };
//            toastThread.start();
//            Log.v(TAG,"toastThread.start();");
            Toast.makeText(context, R.string.question_1_answer_wrong, Toast.LENGTH_LONG).show();
            }

        if(questionNumber == 2 && questionAnswer == 2){
            score++;
            Toast.makeText(context, R.string.right_answer+score, Toast.LENGTH_LONG).show();
        }
        else if(questionNumber == 2){
            Toast.makeText(context, R.string.question_2_answer_wrong, Toast.LENGTH_LONG).show();
        }


    }

}
