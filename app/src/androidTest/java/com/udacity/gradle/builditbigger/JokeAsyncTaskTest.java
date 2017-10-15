package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

/**
 * Created by anh.hoang on 10/15/17.
 */
@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldReturnNonEmptyString() {
        MainActivityFragment.JokeAsyncTask asyncTask = new MainActivityFragment.JokeAsyncTask(activityTestRule.getActivity(), null);

        String result = null;
        try {
            result = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Assert.fail();
        }

        Assert.assertNotNull(result);
        Assert.assertTrue(result.length() > 0);
    }
}
