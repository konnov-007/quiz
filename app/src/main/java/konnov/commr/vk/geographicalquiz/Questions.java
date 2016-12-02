package konnov.commr.vk.geographicalquiz;

import android.content.Context;
import android.graphics.Color;
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
    private int level;

    //private static final String TAG = "Questions";

    Questions(Context c, TextView question_text, Button first_answer_button, Button second_answer_button, Button third_answer_button, Button fourth_answer_button, ImageView question_pic, int level){
        context = c;
        this.question_text = question_text;
        this.first_answer_button = first_answer_button;
        this.second_answer_button = second_answer_button;
        this.third_answer_button = third_answer_button;
        this.fourth_answer_button = fourth_answer_button;
        this.question_pic = question_pic;
        this.level = level;
    }


    public void textForQuestion(int questionNumber){
        switch (level){
            case 1:
                textForLevelOneSetter(questionNumber);
                break;
            case 2:
                //TODO level2
                break;
            case 3:
                textForLevelThreeSetter(questionNumber);
                break;
            case 4:
                //TODO level4
                break;
        }
   }


    public int checkIfAnswerRight(int questionNumber, int questionAnswer){
        switch (level){
            case 1:
                return levelOneChecker(questionNumber, questionAnswer);
            case 2:
                //TODO level2
                break;
            case 3:
                return levelThreeChecker(questionNumber, questionAnswer);
            case 4:
                //TODO level4
                break;
        }
    return -999;
    }











//MISC

    private int levelOneChecker(int questionNumber, int questionAnswer){
        if (questionNumber == 1 && questionAnswer == 2) {
            second_answer_button.setBackgroundColor(Color.GREEN);
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if (questionNumber == 1) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_1_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 2 && questionAnswer == 4) {
            fourth_answer_button.setBackgroundColor(Color.GREEN);
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if (questionNumber == 2) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_2_answer_wrong, Toast.LENGTH_LONG).show();
            //sleep(3500);
            return 0;
        }
        if (questionNumber == 3 && questionAnswer == 2) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            second_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 3) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_3_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 4 && questionAnswer == 1) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            first_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 4) {
            switch (questionAnswer) {
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_4_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 5 && questionAnswer == 1) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            first_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 5) {
            switch (questionAnswer) {
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_5_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 6 && questionAnswer == 4) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            fourth_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 6) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_6_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 7 && questionAnswer == 3) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            third_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 7) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_7_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 8 && questionAnswer == 2) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            second_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 8) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_8_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 9 && questionAnswer == 3) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            third_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 9) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_9_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 10 && questionAnswer == 3) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            third_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 10) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_1_question_10_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        return -999;
    }














    private int levelThreeChecker(int questionNumber, int questionAnswer) {
        if (questionNumber == 1 && questionAnswer == 3) {
            third_answer_button.setBackgroundColor(Color.GREEN);
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if (questionNumber == 1) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_1_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }

        if (questionNumber == 2 && questionAnswer == 2) {
            second_answer_button.setBackgroundColor(Color.GREEN);
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            return 1;
        }
        if (questionNumber == 2) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_2_answer_wrong, Toast.LENGTH_LONG).show();
            //sleep(3500);
            return 0;
        }
        if (questionNumber == 3 && questionAnswer == 4) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            fourth_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 3) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_3_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 4 && questionAnswer == 1) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            first_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 4) {
            switch (questionAnswer) {
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_4_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 5 && questionAnswer == 4) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            fourth_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 5) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_5_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 6 && questionAnswer == 4) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            fourth_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 6) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_6_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 7 && questionAnswer == 2) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            second_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 7) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_7_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 8 && questionAnswer == 3) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            third_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 8) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_8_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 9 && questionAnswer == 1) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            first_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 9) {
            switch (questionAnswer) {
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    third_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_9_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        if (questionNumber == 10 && questionAnswer == 3) {
            Toast.makeText(context, R.string.right_answer, Toast.LENGTH_SHORT).show();
            third_answer_button.setBackgroundColor(Color.GREEN);
            return 1;
        }
        if (questionNumber == 10) {
            switch (questionAnswer) {
                case 1:
                    first_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    second_answer_button.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    fourth_answer_button.setBackgroundColor(Color.RED);
                    break;
            }
            Toast.makeText(context, R.string.level_3_question_10_answer_wrong, Toast.LENGTH_LONG).show();
            return 0;
        }
        return -999;
    }






    private void textForLevelOneSetter(int questionNumber) {
        switch (questionNumber) {
            case 1:
                question_text.setText(R.string.level_1_question_1);
                first_answer_button.setText(R.string.level_1_question_1_answer_1);
                second_answer_button.setText(R.string.level_1_question_1_answer_2);
                third_answer_button.setText(R.string.level_1_question_1_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_1_answer_4);
                break;
            case 2:
                question_text.setText(R.string.level_1_question_2);
                first_answer_button.setText(R.string.level_1_question_2_answer_1);
                second_answer_button.setText(R.string.level_1_question_2_answer_2);
                third_answer_button.setText(R.string.level_1_question_2_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_2_answer_4);
                break;
            case 3:
                question_text.setText(R.string.level_1_question_3);
                first_answer_button.setText(R.string.level_1_question_3_answer_1);
                second_answer_button.setText(R.string.level_1_question_3_answer_2);
                third_answer_button.setText(R.string.level_1_question_3_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_3_answer_4);
                break;
            case 4:
                question_text.setText(R.string.level_1_question_4);
                first_answer_button.setText(R.string.level_1_question_4_answer_1);
                second_answer_button.setText(R.string.level_1_question_4_answer_2);
                third_answer_button.setText(R.string.level_1_question_4_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_4_answer_4);
                break;
            case 5:
                question_text.setText(R.string.level_1_question_5);
                first_answer_button.setText(R.string.level_1_question_5_answer_1);
                second_answer_button.setText(R.string.level_1_question_5_answer_2);
                third_answer_button.setText(R.string.level_1_question_5_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_5_answer_4);
                question_pic.setImageResource(R.drawable.level_1_question_5_pic);
                break;
            case 6:
                question_pic.setImageDrawable(null);
                question_text.setText(R.string.level_1_question_6);
                first_answer_button.setText(R.string.level_1_question_6_answer_1);
                second_answer_button.setText(R.string.level_1_question_6_answer_2);
                third_answer_button.setText(R.string.level_1_question_6_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_6_answer_4);
                break;
            case 7:
                question_text.setText(R.string.level_1_question_7);
                first_answer_button.setText(R.string.level_1_question_7_answer_1);
                second_answer_button.setText(R.string.level_1_question_7_answer_2);
                third_answer_button.setText(R.string.level_1_question_7_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_7_answer_4);
                break;
            case 8:
                question_text.setText(R.string.level_1_question_8);
                first_answer_button.setText(R.string.level_1_question_8_answer_1);
                second_answer_button.setText(R.string.level_1_question_8_answer_2);
                third_answer_button.setText(R.string.level_1_question_8_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_8_answer_4);
                question_pic.setImageResource(R.drawable.level_1_question_8_pic);
                break;
            case 9:
                question_pic.setImageDrawable(null);
                question_text.setText(R.string.level_1_question_9);
                first_answer_button.setText(R.string.level_1_question_9_answer_1);
                second_answer_button.setText(R.string.level_1_question_9_answer_2);
                third_answer_button.setText(R.string.level_1_question_9_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_9_answer_4);
                break;
            case 10:
                question_text.setText(R.string.level_1_question_10);
                first_answer_button.setText(R.string.level_1_question_10_answer_1);
                second_answer_button.setText(R.string.level_1_question_10_answer_2);
                third_answer_button.setText(R.string.level_1_question_10_answer_3);
                fourth_answer_button.setText(R.string.level_1_question_10_answer_4);
            default:
                break;
        }
    }


    private void textForLevelThreeSetter(int questionNumber){
        switch (questionNumber) {
            case 1:
                question_text.setText(R.string.level_3_question_1);
                first_answer_button.setText(R.string.level_3_question_1_answer_1);
                second_answer_button.setText(R.string.level_3_question_1_answer_2);
                third_answer_button.setText(R.string.level_3_question_1_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_1_answer_4);
                break;
            case 2:
                question_text.setText(R.string.level_3_question_2);
                first_answer_button.setText(R.string.level_3_question_2_answer_1);
                second_answer_button.setText(R.string.level_3_question_2_answer_2);
                third_answer_button.setText(R.string.level_3_question_2_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_2_answer_4);
                break;
            case 3:
                question_text.setText(R.string.level_3_question_3);
                first_answer_button.setText(R.string.level_3_question_3_answer_1);
                second_answer_button.setText(R.string.level_3_question_3_answer_2);
                third_answer_button.setText(R.string.level_3_question_3_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_3_answer_4);
                question_pic.setImageResource(R.drawable.level_3_question_3_pic);
                break;
            case 4:
                question_pic.setImageDrawable(null);
                question_text.setText(R.string.level_3_question_4);
                first_answer_button.setText(R.string.level_3_question_4_answer_1);
                second_answer_button.setText(R.string.level_3_question_4_answer_2);
                third_answer_button.setText(R.string.level_3_question_4_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_4_answer_4);
                break;
            case 5:
                question_text.setText(R.string.level_3_question_5);
                first_answer_button.setText(R.string.level_3_question_5_answer_1);
                second_answer_button.setText(R.string.level_3_question_5_answer_2);
                third_answer_button.setText(R.string.level_3_question_5_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_5_answer_4);
                question_pic.setImageResource(R.drawable.level_3_question_5_pic);
                break;
            case 6:
                question_pic.setImageDrawable(null);
                question_text.setText(R.string.level_3_question_6);
                first_answer_button.setText(R.string.level_3_question_6_answer_1);
                second_answer_button.setText(R.string.level_3_question_6_answer_2);
                third_answer_button.setText(R.string.level_3_question_6_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_6_answer_4);
                break;
            case 7:
                question_text.setText(R.string.level_3_question_7);
                first_answer_button.setText(R.string.level_3_question_7_answer_1);
                second_answer_button.setText(R.string.level_3_question_7_answer_2);
                third_answer_button.setText(R.string.level_3_question_7_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_7_answer_4);
                question_pic.setImageResource(R.drawable.level_3_question_7_pic);
                break;
            case 8:
                question_pic.setImageDrawable(null);
                question_text.setText(R.string.level_3_question_8);
                first_answer_button.setText(R.string.level_3_question_8_answer_1);
                second_answer_button.setText(R.string.level_3_question_8_answer_2);
                third_answer_button.setText(R.string.level_3_question_8_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_8_answer_4);
                break;
            case 9:
                question_text.setText(R.string.level_3_question_9);
                first_answer_button.setText(R.string.level_3_question_9_answer_1);
                second_answer_button.setText(R.string.level_3_question_9_answer_2);
                third_answer_button.setText(R.string.level_3_question_9_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_9_answer_4);
                break;
            case 10:
                question_text.setText(R.string.level_3_question_10);
                first_answer_button.setText(R.string.level_3_question_10_answer_1);
                second_answer_button.setText(R.string.level_3_question_10_answer_2);
                third_answer_button.setText(R.string.level_3_question_10_answer_3);
                fourth_answer_button.setText(R.string.level_3_question_10_answer_4);
                question_pic.setImageResource(R.drawable.level_3_question_10_pic);
                break;

            default:
                break;
        }
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


