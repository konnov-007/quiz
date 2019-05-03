package konnov.commr.vk.geographicalquiz.mainmenu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import konnov.commr.vk.geographicalquiz.Injection;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.about.AboutActivity;
import konnov.commr.vk.geographicalquiz.levelselector.LevelSelectorActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainMenuActivity extends AppCompatActivity implements MainMenuContract.View {

    private MainMenuPresenter mPresenter;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mPresenter = new MainMenuPresenter(Injection.provideQuestionsRepository(this));
        initUI();
    }

    private void initUI() {
        Toolbar toolbar = findViewById(R.id.main_menu_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        mProgressDialog = new ProgressDialog(MainMenuActivity.this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_game_button: {
                startNewGame();
                break;
            }
            case R.id.about_button: {
                startAbout();
                break;
            }
            case R.id.update_db_button: {
                updateDB();
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
        mPresenter.fetchQuestions();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.dropView();
    }

    @Override
    public void showLoadingQuestionsError() {
        mProgressDialog.dismiss();
        showMessage(getString(R.string.network_error));
    }

    @Override
    public void showUpdatingQuestionsSuccess() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showLoadingQuestionsStarted() {
        mProgressDialog.setMessage(getResources().getString(R.string.database_loading));
        mProgressDialog.show();
    }

    private void showMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    private void startNewGame() {
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        startActivity(intent);
    }

    private void startAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    private void updateDB() {
        mPresenter.updateQuestions();
    }
}
