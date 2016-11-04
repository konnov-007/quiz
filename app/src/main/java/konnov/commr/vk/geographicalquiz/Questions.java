package konnov.commr.vk.geographicalquiz;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ilya on 04.10.2016.
 */
public class Questions extends AppCompatActivity{
    private static Context context;
    private TextView question_text;
    private Button first_answer_button;
    private Button second_answer_button;
    private Button third_answer_button;
    private Button fourth_answer_button;
    private ImageView question_pic;

    //private static final String TAG = "Questions";

    Questions(Context c, TextView question_text, Button first_answer_button, Button second_answer_button, Button third_answer_button, Button fourth_answer_button, ImageView question_pic){
        context = c;
        this.question_text = question_text;
        this.first_answer_button = first_answer_button;
        this.second_answer_button = second_answer_button;
        this.third_answer_button = third_answer_button;
        this.fourth_answer_button = fourth_answer_button;
        this.question_pic = question_pic;
    }


    public void textForQuestion(int questionNumber){
        switch (questionNumber){
            case 2:
                question_text.setText(R.string.question_2);
                first_answer_button.setText(R.string.question_2_answer_1);
                second_answer_button.setText(R.string.question_2_answer_2);
                third_answer_button.setText(R.string.question_2_answer_3);
                fourth_answer_button.setText(R.string.question_2_answer_4);
                break;
            case 3:
                question_text.setText(R.string.question_3);
                first_answer_button.setText(R.string.question_3_answer_1);
                second_answer_button.setText(R.string.question_3_answer_2);
                third_answer_button.setText(R.string.question_3_answer_3);
                fourth_answer_button.setText(R.string.question_3_answer_4);
                question_pic.setImageResource(R.drawable.question_3_pic);
                break;
            case 4:
                question_pic.setImageDrawable(null);
                question_text.setText(R.string.question_4);
                first_answer_button.setText(R.string.question_4_answer_1);
                second_answer_button.setText(R.string.question_4_answer_2);
                third_answer_button.setText(R.string.question_4_answer_3);
                fourth_answer_button.setText(R.string.question_4_answer_4);
                break;
            default:break;
        }
   }

    public int checkIfAnswerRight(int questionNumber, int questionAnswer){

        if(questionNumber == 1 && questionAnswer == 3){
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            //sleep(2000);
            return 1;
        }
        if(questionNumber == 1){
            Toast.makeText(context, R.string.question_1_answer_wrong, Toast.LENGTH_LONG).show();
            //sleep(3500);
            return 0;
        }

        if(questionNumber == 2 && questionAnswer == 2){
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            //sleep(2000);
            return 1;
        }
        if(questionNumber == 2){
            Toast.makeText(context, R.string.question_2_answer_wrong, Toast.LENGTH_LONG).show();
            //sleep(3500);
            return 0;
        }
        if(questionNumber == 3 && questionAnswer == 4){
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(questionNumber == 3){
            Toast.makeText(context, R.string.question_3_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if(questionNumber == 4 && questionAnswer == 1){
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(questionNumber == 4){
            Toast.makeText(context, R.string.question_4_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

    return -999;
    }
}

















//    private void sleep(final int delayTime) {
////        Runnable r = new Runnable() {
////            @Override
////            public void run() {
////                long futureTime = System.currentTimeMillis() + delayTime;
////                while (System.currentTimeMillis() < futureTime) {
////                    synchronized (this) {
////                        try {
////                            wait(futureTime - System.currentTimeMillis());
////                        } catch (Exception e) {
////                        }
////                    }
////                }
////            }
////        };
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(delayTime);
//                } catch (Exception e) {}
//            }
//        };
//        Thread thread = new Thread(r);
//        thread.start();
//    }


