package konnov.commr.vk.geographicalquiz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import konnov.commr.vk.geographicalquiz.R;
import konnov.commr.vk.geographicalquiz.interfaces.DataStorageModel;
import konnov.commr.vk.geographicalquiz.interfaces.Interfaces;

public class MainMenuActivity extends AppCompatActivity implements DataStorageModel {
    private Interfaces interfaces = Interfaces.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        interfaces.subscribeDataStorageModel(getClass().getName(), this);
    }

    public void newGame(View view){
        Intent intent = new Intent(this, LevelSelector.class);
        startActivity(intent);
        finish();
    }


    public void about(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        finish();
    }



    @Override
    public void onBackPressed(){
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        interfaces.unscubscribeDataStorageModel(getClass().getName());
        finish();
    }

    @Override
    public void questionsReceived(ArrayList<Object> questions) {
        System.out.println(questions);
    }

    @Override
    public void translationsReceived(ArrayList<Object> translations) {
        System.out.println(translations);
    }
}
