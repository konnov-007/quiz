package konnov.commr.vk.geographicalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LevelSelector extends AppCompatActivity {

    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);
    }

    public void startQuestions(android.view.View v) {
        switch (v.getId()) {
            case R.id.button:
                Toast.makeText(this, "level 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this, "level 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                level = 3;
                Intent intent = new Intent(this, MainGameActivity.class);
                intent.putExtra("level", level);
                startActivity(intent);
                break;
            case R.id.button4:
                Toast.makeText(this, "level 4", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

