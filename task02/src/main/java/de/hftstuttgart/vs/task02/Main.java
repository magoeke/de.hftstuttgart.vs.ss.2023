package de.hftstuttgart.vs.task02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.javalin.Javalin;

public class Main {

    /**
     * Record that represents a user.
     *
     * @param id find a better type the object (There are multiple solutions)
     * @param firstName first name of user
     * @param lastName last name of user
     */
    public record User(Object id, String firstName, String lastName) {}

    /**
     * Why do we need a synchronized list?
     */
    private final List<User> users = Collections.synchronizedList(new ArrayList<>());

    /**
     * Develop a rest api which can execute the following tasks:
     * - get a list of all users (return empty array if there is no user stored)
     * - return a single user by their id (return 404 if user doesn't exist)
     * - create a new user (return the correct http status)
     *
     * Beside developing the functionalities you should also define the endpoints.
     *
     * Further Information about http status codes: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
     *
     * @param args can be ignored
     */
    public static void main(final String[] args) {
        final var app = Javalin.create()
                .start(7070);

        app.get("/", ctx -> ctx.json(new User("test_id", "Max", "Mustermann")));
    }


}
