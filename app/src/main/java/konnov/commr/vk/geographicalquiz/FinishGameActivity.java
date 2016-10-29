package konnov.commr.vk.geographicalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishGameActivity extends AppCompatActivity {
    TextView finishtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);

        finishtext = (TextView) findViewById(R.id.finish_text_view);

        Intent intent = getIntent();
        int finishscore = intent.getIntExtra("score_value", 0);

        finishtext.setText(R.id.finish_text_view + finishscore);
    }
}