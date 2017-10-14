/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.anh.hoang.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.jokes.JokeGenerator;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.hoang.anh.com",
                ownerName = "backend.myapplication.hoang.anh.com",
                packagePath = ""
        )
)
public class JokeEndpoint {
    private final JokeGenerator jokeGenerator;

    public JokeEndpoint() {
        jokeGenerator = new JokeGenerator();
    }

    @ApiMethod(name = "getJoke")
    public JokeBean getJoke() {
        JokeBean response = new JokeBean(jokeGenerator.getRandomJoke());

        return response;
    }

}
