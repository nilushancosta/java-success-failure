FROM amazoncorretto:17-alpine3.17 as builder
RUN apk update; apk add maven
WORKDIR /tmp/build
COPY src /tmp/build/src
COPY pom.xml /tmp/build
RUN mvn install
USER 10014

FROM amazoncorretto:17-alpine3.17
RUN apk update; apk add maven curl

COPY --from=builder /tmp/build/target/byocjava-0.0.1-SNAPSHOT.jar .
RUN wget -O dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'



USER 10014

CMD java -javaagent:dd-java-agent.jar \
         -Ddd.profiling.enabled=true \
         -XX:FlightRecorderOptions=stackdepth=256 \
         -Ddd.logs.injection=true \
         -jar byocjava-0.0.1-SNAPSHOT.jar
