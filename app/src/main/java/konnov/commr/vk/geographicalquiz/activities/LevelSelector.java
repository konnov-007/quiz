package konnov.commr.vk.geographicalquiz.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import konnov.commr.vk.geographicalquiz.R;

public class LevelSelector extends AppCompatActivity {

    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.select_level_in_selector_activity);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void startQuestions(android.view.View v) {
        Intent intent = new Intent(this, GameActivity.class);
        switch (v.getId()) {
            case R.id.button:
                level = 1; //TODO - 0
                intent.putExtra("level", level);
                startActivity(intent);
                break;
            case R.id.button2:
                Toast.makeText(this, "level 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                level = 3;
                intent.putExtra("level", level); //TODO - DELETE
                startActivity(intent);
                break;
            case R.id.button4:
                Toast.makeText(this, "level 4", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, MainMenuActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainMenuActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}

