package com.anh.hoang.myapplication.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class JokeBean {

    private String joke;

    public JokeBean(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }
}