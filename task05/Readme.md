# Getting Started

This task might seem familiar for you ;) The project contains everything to get started with a grpc service.
Don't worry if it shows errors. Try to run `maven compile`. It should fix those errors. Restarting IntelliJ
afterwards also helps.

Right now the project only provides a Hello World example for grpc.

# Task 05

Let's develop a simple blog service. It's possible to create, update and delete posts. Not every post is
published. You also need to provide a way to lock posts so that an author can edit them. 
Users can comment posts. A post contains the text itself, a state and the
id of the author. A comment consist of the text and the user id. Authors are also users.
Users won't be manged by this service, so we only deal with the ids.

1. Define your `*.proto` file
3. Generate all necessary files (`maven compile`)
4. Implement a simple client and server (you don't need to store any data - static return are valid)




