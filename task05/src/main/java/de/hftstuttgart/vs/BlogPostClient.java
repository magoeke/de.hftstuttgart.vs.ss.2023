package de.hftstuttgart.vs;

import com.google.protobuf.Empty;
import io.grpc.*;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class BlogPostClient {

    private final BlogPostServiceGrpc.BlogPostServiceBlockingStub blockingStub;

    public BlogPostClient(Channel channel) {
        blockingStub = BlogPostServiceGrpc.newBlockingStub(channel);
    }

    public void getAllBlogPosts() {
        System.out.println("Will try to get all blog posts");
        Iterator<BlogPost> response;
        try {
            response = blockingStub
                    .getBlogPosts(Empty.getDefaultInstance());
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
            return;
        }
        System.out.println("Response: " + response.next());
    }

    public void addBlogPost() {
        BlogPostRequest blogPostRequest = BlogPostRequest
                .newBuilder()
                .setBlogPostStatus(BlogPostStatus.DRAFT)
                .setAuthorId(7)
                .setLocked(false)
                .setContent("Hello World 2")
                .build();

        System.out.println("Will try to add a blog post");
        BlogPost response;
        try {
            response = blockingStub
                    .createBlogPost(blogPostRequest);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
            return;
        }
        System.out.println("Response: " + response);
    }

    public static void main(String[] args) throws Exception {
        String target = "localhost:50051";

        ManagedChannel channel = Grpc
                .newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();

        try {
            BlogPostClient client = new BlogPostClient(channel);
            client.getAllBlogPosts();
            client.addBlogPost();
        } finally {
            channel
                    .shutdownNow()
                    .awaitTermination(5, TimeUnit.SECONDS);
        }
    }

}
