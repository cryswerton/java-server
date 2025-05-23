FROM openjdk:17

RUN mkdir /app

COPY out/production/Server/ /app

WORKDIR /app

EXPOSE 8080

CMD ["java", "Main"]