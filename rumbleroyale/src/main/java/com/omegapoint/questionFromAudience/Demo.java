package com.omegapoint.questionFromAudience;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {
    static HttpClient client = HttpClient.newHttpClient();
    static int MAX = 500;
    static final List<String> URLS = Stream.generate(() -> "https://www.google.se")
            .limit(MAX)
            .toList();

    void main() {

    }


























    private void runOneMillionTasks(){
        final long start = System.currentTimeMillis();
        var max = 100_000_000;
        final List<Integer> collect = IntStream.range(0, max)
                .parallel()
                .map(operand -> operand + 1)
                .boxed()
//                .gather(Gatherers.mapConcurrent(100, integer -> integer + 1))
                .toList();
        final long end = System.currentTimeMillis();
        System.out.println(STR."Took \{end - start} ms");
    }

    private String call(final String url) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        try {
            final HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
            return send.body().substring(0,10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
