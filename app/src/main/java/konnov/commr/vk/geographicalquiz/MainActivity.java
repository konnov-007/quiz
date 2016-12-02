package konnov.commr.vk.geographicalquiz;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import konnov.commr.vk.geographicalquiz.about_package.AboutActivity;
import konnov.commr.vk.geographicalquiz.about_package.AboutFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGameMethod(View view){
        Intent intent = new Intent(this, LevelSelector.class);
        startActivity(intent);
        finish();
    }


    public void aboutMethod(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        finish();
    }

    public void exitMethod(View view){
        finish();
    }



    @Override
    public void onBackPressed(){
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}
