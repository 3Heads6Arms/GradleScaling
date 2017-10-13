package com.anhhoang.jokeandroidlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        if (!intent.hasExtra(Intent.EXTRA_TEXT)) {
            throw new IllegalArgumentException("Missing EXTRA_TEXT extra");
        }
        String joke = intent.getStringExtra(Intent.EXTRA_TEXT);

        ((TextView) findViewById(R.id.joke_text_view)).setText(joke);
    }

    public static Intent getStartingIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, joke);

        return intent;
    }
}
