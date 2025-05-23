FROM openjdk:17

RUN mkdir /app
WORKDIR /app

COPY src/ /app

RUN javac Main.java

EXPOSE 8080

CMD ["java", "Main"]