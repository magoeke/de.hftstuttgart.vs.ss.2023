package de.hftstuttgart.vs.task02;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
     * Why do we use ConcurrentHashMap?
     * find a better type for Object (see description above)
     */
    private static final Map<Object, User> users = new ConcurrentHashMap<>();

    /**
     * Develop a rest api which can execute the following tasks:
     * - get a list of all users (return empty array if there is no user stored)
     * - return a single user by their id (return 404 if user doesn't exist)
     * - create a new user (return the correct http status)
     *
     * Beside developing the functionalities you should also define the endpoints.
     *
     * Further Information about http status codes: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
     * More information about javalin: https://javalin.io/documentation#context
     *
     * @param args can be ignored
     */
    public static void main(final String[] args) {
        final var app = Javalin.create()
                .start(7070);

        app.get("/", ctx -> ctx.json(new User("test_id", "Max", "Mustermann")));
    }


}
