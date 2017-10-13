package com.jokes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeGenerator {
    private final Random random;
    private List<String> jokes;

    public JokeGenerator() {
        // instantiation of random each call causes some random to repeat the number, so creating it as field fixes it.
        random = new Random();
        jokes = new ArrayList<>();

        jokes.add("Why do chicken coops only have two doors? Because if they had four, they would be chicken sedans!");
        jokes.add("Why did the Clydesdale give the pony a glass of water? Because he was a little horse!");
        jokes.add("How do you make holy water? You boil the hell out of it.");
        jokes.add("I had a dream that I was a muffler last night. I woke up exhausted!");
    }

    public String getRandomJoke() {
        int jokeIndex = random.nextInt(jokes.size());

        return jokes.get(jokeIndex);
    }
}
