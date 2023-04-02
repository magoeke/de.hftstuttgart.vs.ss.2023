# Getting Started

This project contains everything to get started with an api first spring service.
Don't worry if it shows errors. Try to run `maven compile`. It should fix those errors.

In the beginning it uses the default open api example (Pet Store - without the file upload).
You can find the spec in `src/main/resources/api.yaml`. For a better understanding you can view
it here: https://editor-next.swagger.io/

When starting the spring service - execute Task03Application - go to http://localhost:8080/swagger-ui.html.
Execute /api/v3/pet/{petId}. It's the only enpoint that's "implemented".

# Task 03

Let's develop a simple blog service. It's possible to create, update and delete posts. Not every post is 
published. You also need to provide a way to lock posts so that an author can edit them. See the Zalando
api guid for inspiration. Users can comment under posts. A post contains the text itself, a state and the
id of the author. A comment consist of the text and the user id. Authors are also users.
Users won't be manged by this service so we only deal with the ids.

1. Think about the resource you need - keep the best practices in mind
2. Design your api in https://editor-next.swagger.io/
3. Copy your api into `src/main/resources/api.yaml`
4. Execute `maven clean complie` - do this everytime your api changes
5. Implement static returns (you don't need to store the requests, just "fake it")





