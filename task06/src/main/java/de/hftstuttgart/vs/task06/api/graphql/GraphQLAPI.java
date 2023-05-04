package de.hftstuttgart.vs.task06.api.graphql;

import de.hftstuttgart.vs.task06.api.graphql.model.HelloWorldDO;
import de.hftstuttgart.vs.task06.bm.BlogController;
import jakarta.annotation.Nonnull;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLAPI implements QueryResolver {

    private final BlogController blogController;

    public GraphQLAPI(final BlogController blogController) {
        this.blogController = blogController;
    }

    @Nonnull
    @Override
    @QueryMapping
    public HelloWorldDO helloWorld() {
        return new HelloWorldDO();
    }

    @SchemaMapping
    public String message(final HelloWorldDO helloWorld) {
        return "Hello World!";
    }

    //TODO 3: Add mappings for queries and mutations defined in your schema

    //TODO 5: Add field mapping for count of comments for a post
}
