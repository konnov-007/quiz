package konnov.commr.vk.geographicalquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGameMethod(View view){
        Intent intent = new Intent(this, MainGameActivity.class);
        startActivity(intent);
    }

    public void feedbackMethod(View view){
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mr.konnov"));
        startActivity(browseIntent);
    }

    public void exitMethod(View view){
        finish();
    }

}
