FROM ubuntu:latest
WORKDIR /working
COPY build/libs/HelloWorldFunction-all.jar HelloWorldFunction-all.jar
COPY src/main/resources/reflect.json reflect.json
RUN apt update -y && apt upgrade -y
RUN apt install wget -y
RUN apt install gcc -y
RUN wget https://download.oracle.com/graalvm/22/latest/graalvm-jdk-22_linux-x64_bin.tar.gz
RUN tar -xvzf graalvm-jdk-22_linux-x64_bin.tar.gz
RUN graalvm-jdk-22+36.1/bin/native-image --enable-url-protocols=http,https \
                                             -H:ReflectionConfigurationFiles=reflect.json \
                                             -H:+ReportUnsupportedElementsAtRuntime --no-server -jar "HelloWorldFunction-all.jar" \
                                             && cp HelloWorldFunction-all /working/build/graalvm/server
