package com.omegapoint.paralell;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

public class Demo {
    static final List<String> URLS = IntStream.range(0, 500).mapToObj(_ -> "https://www.google.se").toList();

    void main() throws Exception {
        URLS.stream()
                .gather(Gatherers.mapConcurrent(500, this::call))
                .forEach(System.out::println);
    }

    String call(final String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();

        try {
            final HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
            return send.body().substring(0,10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
