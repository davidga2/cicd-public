FROM openjdk:21

WORKDIR /myContainer
COPY ./target/ci-cd.war /myContainer
EXPOSE 1234
CMD [ "java","-jar","ci-cd.war" ]