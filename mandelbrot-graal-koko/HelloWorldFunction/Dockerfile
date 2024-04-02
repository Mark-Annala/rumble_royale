# Stage 1: Use the GraalVM image to build the native binary
FROM ghcr.io/graalvm/graalvm-ce:21 AS builder

WORKDIR /working

# Install native-image
RUN /usr/lib/jvm/graalvm-ce-21/bin/gu install native-image

# Build the native binary
COPY . .

RUN native-image --enable-url-protocols=http,https \
    -H:ReflectionConfigurationFiles=/working/src/main/resources/reflect.json \
    -H:+ReportUnsupportedElementsAtRuntime --no-server -jar "/working/build/libs/HelloWorldFunction-all.jar" \
    && cp HelloWorldFunction-all /working/build/graalvm/server

# Stage 2: Use a lightweight image to copy the built native binary
FROM alpine:latest

WORKDIR /app

# Copy the built native binary from the builder stage
COPY --from=builder /working/build/graalvm/server /app/server

# Create a directory for graalvm build
RUN mkdir -p /build/graalvm

# Check if the server binary exists
RUN if [ ! -f "/app/server" ]; then \
        echo "There was an error building graalvm image"; \
        exit 1; \
    fi

CMD ["echo", "GraalVM image with native binary built successfully."]
