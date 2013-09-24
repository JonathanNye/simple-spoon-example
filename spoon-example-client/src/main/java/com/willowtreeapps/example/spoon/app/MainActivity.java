package com.willowtreeapps.example.spoon.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button textChangingButton = (Button) findViewById(R.id.text_changing_button);
        textChangingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textChangingButton.setText(R.string.button_text_2);
            }
        });

        View viewAppearingButton = findViewById(R.id.view_appearing_button);
        final View viewToAppear = findViewById(R.id.view_to_appear);
        viewAppearingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewToAppear.setVisibility(View.VISIBLE);
            }
        });

        View navigatingButton = findViewById(R.id.navigating_button);
        navigatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
            }
        });

    }

}

