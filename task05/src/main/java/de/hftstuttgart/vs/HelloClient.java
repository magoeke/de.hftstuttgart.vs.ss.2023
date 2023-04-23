package de.hftstuttgart.vs;


import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloClient {

    private static final Logger logger = Logger.getLogger(HelloClient.class.getName());

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloClient(Channel channel) {
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub
                    .sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }

    public static void main(String[] args) throws Exception {
        String user = "world";
        String target = "localhost:50051";

        ManagedChannel channel = Grpc
                .newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();

        try {
            HelloClient client = new HelloClient(channel);
            client.greet(user);
        } finally {
            channel
                    .shutdownNow()
                    .awaitTermination(5, TimeUnit.SECONDS);
        }
    }

}
