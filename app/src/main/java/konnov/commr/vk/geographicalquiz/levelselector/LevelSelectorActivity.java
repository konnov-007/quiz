package konnov.commr.vk.geographicalquiz.levelselector;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.game.GameActivity;
import konnov.commr.vk.geographicalquiz.mainmenu.MainMenuActivity;

public class LevelSelectorActivity extends AppCompatActivity implements  LevelSelectorContract.View{

    private LevelSelectorContract.Presenter mPresenter = new LevelSelectorPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);
        initUI();
    }

    private void initUI(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.select_level_in_selector_activity);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void startQuestions(View v) {
        Intent intent = new Intent(this, GameActivity.class);

        switch (v.getId()) {
            case R.id.easy_level_button:
                int level = 0;
                intent.putExtra("level", level);
                startActivity(intent);
                break;
            case R.id.medium_level_button: //TODO implement other difficulties
                level = 1;
//                intent.putExtra("level 2", level);
//                startActivity(intent);
                break;
            case R.id.difficult_level_button:
//                level = 2;
//                Toast.makeText(this, "level 4", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.dropView();
    }

}

