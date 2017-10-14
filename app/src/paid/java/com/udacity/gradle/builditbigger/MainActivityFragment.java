package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.anh.hoang.myapplication.backend.jokesApi.JokesApi;
import com.anhhoang.jokeandroidlibrary.*;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.lang.ref.WeakReference;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private ProgressBar progressBar;
    private View btnTellJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        btnTellJoke = root.findViewById(R.id.tell_joke_button);
        btnTellJoke.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        new JokeAsyncTask(getContext(), progressBar).execute();
    }

    public static class JokeAsyncTask extends AsyncTask<Void, Void, String> {
        private JokesApi jokesApi;

        private WeakReference<ProgressBar> progressBarRef;
        private WeakReference<Context> contextRef;

        public JokeAsyncTask(Context context, ProgressBar progressBar) {
            contextRef = new WeakReference<>(context);
            progressBarRef = new WeakReference<>(progressBar);
        }

        @Override
        protected void onPreExecute() {
            if (progressBarRef.get() != null) {
                progressBarRef.get().setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            builder.setRootUrl("http://10.0.2.2:8080/_ah/api/");

            jokesApi = builder.build();

            try {
                return jokesApi.getJoke().execute().getJoke();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String jokeResult) {
            if (progressBarRef.get() != null) {
                progressBarRef.get().setVisibility(View.INVISIBLE);
            }
            if (contextRef.get() != null && jokeResult != null) {
                Context context = contextRef.get();
                context.startActivity(JokeActivity.getStartingIntent(context, jokeResult));
            }
        }
    }
}
