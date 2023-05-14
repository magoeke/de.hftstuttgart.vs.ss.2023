package de.hftstuttgart.vs;

import com.google.protobuf.Empty;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BlogPostServer {

    private Server server;


    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new BlogPostImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    BlogPostServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final BlogPostServer server = new BlogPostServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class BlogPostImpl extends BlogPostServiceGrpc.BlogPostServiceImplBase {

        @Override
        public void getBlogPosts(Empty request, StreamObserver<BlogPost> responseObserver) {
            BlogPost reply = BlogPost
                    .newBuilder()
                    .setBlogPostId(1L)
                    .setBlogPostStatus(BlogPostStatus.DRAFT)
                    .setAuthorId(7L)
                    .setContent("Hello World")
                    .setLocked(false)
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void createBlogPost(BlogPostRequest request, StreamObserver<BlogPost> responseObserver) {
            BlogPost reply = BlogPost
                    .newBuilder()
                    .setBlogPostId(2L)
                    .setBlogPostStatus(request.getBlogPostStatus())
                    .setAuthorId(request.getAuthorId())
                    .setContent(request.getContent())
                    .setLocked(request.getLocked())
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

}
