package konnov.commr.vk.geographicalquiz.gamefinish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.mainmenu.MainMenuActivity;

public class FinishGameActivity extends AppCompatActivity {
    TextView resultTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);

        resultTV = (TextView) findViewById(R.id.finish_text_view);

        Intent intent = getIntent();
        int finishScore = intent.getIntExtra("score", 0);
        int questionsNumber = intent.getIntExtra("questions_number", 0);
        String outputString = getString(R.string.congrats, finishScore, questionsNumber);
        resultTV.setText(outputString);
    }

    @Override
    public void onBackPressed() {
        startMainMenuActivity(null);
    }

    /**
     * This method is either called when you click back button
     * or when you click return to main menu button
     */
    public void startMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
