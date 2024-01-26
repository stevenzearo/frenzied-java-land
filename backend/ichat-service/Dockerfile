FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","ichat-service", "&&", "useradd", "-r", "-g", "ichat-service", "ichat-service"]
ADD ./build/distributions/ichat-service-0.0.1-SNAPSHOT.tar /app
CMD ["USER", "ichat-service"]
CMD ["/app/ichat-service-0.0.1-SNAPSfHOT/bin/ichat-service"]