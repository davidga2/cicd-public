FROM openjdk:21

WORKDIR /myContainer
COPY ./target/ci-cd.jar /myContainer
EXPOSE 1234
CMD [ "java","-jar","ci-cd.jar" ]