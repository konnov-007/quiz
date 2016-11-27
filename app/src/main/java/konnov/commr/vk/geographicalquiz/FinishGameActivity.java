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
        int finishScore = intent.getIntExtra("int_score", 0);
        String outputString = getString(R.string.congrats, finishScore);
        finishtext.setText(outputString);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, LevelSelector.class));
        finish();
    }
}
