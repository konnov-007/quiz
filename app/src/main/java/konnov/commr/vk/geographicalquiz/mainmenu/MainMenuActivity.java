package konnov.commr.vk.geographicalquiz.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import konnov.commr.vk.geographicalquiz.Injection;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.about.AboutActivity;
import konnov.commr.vk.geographicalquiz.interfaces.Interfaces;
import konnov.commr.vk.geographicalquiz.levelselector.LevelSelectorActivity;

public class MainMenuActivity extends AppCompatActivity implements MainMenuContract.View{
    private MainMenuPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainMenuPresenter(Injection.provideQuestionsRepository(this));
        initUI();
    }

    @Override
    public void initUI() {
        mPresenter.fetchQuestions();
        Button newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGame();
            }
        });

        Button aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAbout();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onBackPressed(){
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.dropView();
    }

    @Override
    public void showErrorMessage() {
        //todo error message if no internet connection
    }

    @Override
    public void startNewGame() {
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
