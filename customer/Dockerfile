FROM openjdk:11.0.1-jre-slim
COPY build/libs/*-all.jar customer.jar
CMD java ${JAVA_OPTS} -jar customer.jar