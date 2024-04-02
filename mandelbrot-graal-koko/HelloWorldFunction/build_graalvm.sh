#!/bin/bash

docker run --rm -v $(pwd):/working ghcr.io/graalvm/graalvm-community:21 \
    /bin/bash -c "
                    gu install native-image; \
                    native-image --enable-url-protocols=http,https \
                      -H:ReflectionConfigurationFiles=/working/src/main/resources/reflect.json \
                      -H:+ReportUnsupportedElementsAtRuntime --no-server -jar \"/working/build/libs/HelloWorldFunction-all.jar\" \
                    ; \
                    cp HelloWorldFunction-all /working/build/graalvm/server"

mkdir -p build/graalvm
if [ ! -f "build/graalvm/server" ]; then
    echo "there was an error building graalvm image"
    exit 1
fi
