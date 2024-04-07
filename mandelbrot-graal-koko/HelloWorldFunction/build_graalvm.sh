#!/bin/bash


graalvm-jdk-22+36.1/bin/native-image --enable-url-protocols=http,https \
  -H:ReflectionConfigurationFiles=/src/main/resources/reflect.json \
  -H:+ReportUnsupportedElementsAtRuntime --no-server -jar \"/build/libs/HelloWorldFunction-all.jar\ \
; \
cp HelloWorldFunction-all /working/build/graalvm/server"

mkdir -p build/graalvm
if [ ! -f "build/graalvm/server" ]; then
    echo "there was an error building graalvm image"
    exit 1
fi
