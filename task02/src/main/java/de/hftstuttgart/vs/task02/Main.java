package de.hftstuttgart.vs.task02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.javalin.Javalin;
import io.javalin.http.Context;

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
     *
     * @param args can be ignored
     */
    public static void main(final String[] args) {
        final var app = Javalin.create()
                .start(7070);

        app.get("/users", ctx -> ctx.json(users.values()));
        app.get("/users/{userId}", Main::getUser);
        app.post("/users", Main::addUser);
    }

    private static void getUser(final Context ctx) {
        final var userId = ctx.pathParam("userId");

        final var user = users.get(userId);

        if(user != null) {
            ctx.json(user);
        } else {
            ctx.status(404);
        }
    }

    private static void addUser(final Context ctx) {
        final var user = ctx.bodyAsClass(User.class);

        users.put(user.id, user);

        ctx.status(201);
    }



}
