package com.example.grpc;

import io.grpc.*;

/*
    For simplicity of this sample, we use the same Maven project, but simply
    add a new Client class with a new main method.
*/

public class Client
{
    public static void main( String[] args ) throws Exception
    {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        // It is up to the client to determine whether to block the call
        // Here we create a blocking stub, but an async stub,
        // or an async stub with Future are always possible.
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request =
                GreetingServiceOuterClass.HelloRequest.newBuilder()
                        .setName("Kathryn")
                        .build();

        // Finally, make the call using the stub
        GreetingServiceOuterClass.HelloResponse response =
                stub.greeting(request);

        System.out.println(response);

        // A Channel should be shutdown before stopping the process.
        channel.shutdownNow();
    }
}