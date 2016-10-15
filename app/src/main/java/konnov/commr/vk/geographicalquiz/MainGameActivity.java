package konnov.commr.vk.geographicalquiz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener{
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

        first_answer_button.setOnClickListener(this);
        second_answer_button.setOnClickListener(this);
        third_answer_button.setOnClickListener(this);
        fourth_answer_button.setOnClickListener(this);
       Questions questions = new Questions();
        questions.mainGameMethod(1, 0);
    }

    @Override
    public void onClick(View v) {
        //TODO Auto-generated method stub
        Questions questions = new Questions();
             if(v.getId() == R.id.answer1)
                 questions.mainGameMethod(1, 1);
            if(v.getId() == R.id.answer2)
                questions.mainGameMethod(1, 2);
            if(v.getId() == R.id.answer3)
                questions.mainGameMethod(1, 3);
            if(v.getId() == R.id.answer4)
                questions.mainGameMethod(1, 4);
        }
}

