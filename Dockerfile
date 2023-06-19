FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/chanban-0.0.1-SNAPSHOT.jar /app/ChanBanApplication.jar

EXPOSE 8080

CMD ["java", "-jar", "ChanBanApplication.jar"]