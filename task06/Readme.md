# Getting Started

This task might seem familiar for you ;) The project contains everything to get started with a GraphQL service.
Don't worry if it shows errors. Try to run `maven compile`. It should fix those errors. Restarting IntelliJ
afterwards also helps.

Right now the project only provides a Hello World example for GraphQL.

You can access GraphiQL with the url [http://localhost:8080/graphiql](http://localhost:8080/graphiql).

You can use this query for the hello world example:
```
query helloWorld {
  helloWorld {
    message
  }
}
```

# Task 06

Let's develop a simple blog service. It's possible to create, update and delete posts. Not every post is
published. You also need to provide a way to lock posts so that an author can edit them.
Users can comment posts. A post contains the text itself, a state and the author. It is also possible to get the total
count of comments for the post. A comment consist of the text and the user. Authors are also users. Users are managed by
this service. They have a username as well as a first name and last name.

1. Define your `*.graphqls` file
2. Generate all necessary files (`maven compile`)
3. Implement a simple server using the `GraphQLAPI`-Controller. You may use `BlogController` and the given methods. The
   already implemented mappers will help you, just remove the comments.
4. Test your API using GraphiQL or using the integrated GraphQL-Client in your IDE
