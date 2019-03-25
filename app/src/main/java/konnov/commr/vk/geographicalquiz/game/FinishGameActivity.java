package konnov.commr.vk.geographicalquiz.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.levelselector.LevelSelectorActivity;

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
        startActivity(new Intent(this, LevelSelectorActivity.class));
        finish();
    }


}
