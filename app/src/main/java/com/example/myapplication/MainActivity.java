package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.fakeLocation.ui.FakeMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvMainTest = findViewById(R.id.x_posed_main_test_tv);

        setMainTestText(tvMainTest, "没有被xposed");

        Log.d("FakeMainActivity", "onCreate(Bundle savedInstanceState) ... ");

        Button btOpenFakeLocationView = findViewById(R.id.x_posed_main_open_fake_location_bt);
        btOpenFakeLocationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FakeMainActivity.class));
            }
        });
    }

    private void setMainTestText(TextView tv, String text) {
        tv.setText(text);
    }
}
