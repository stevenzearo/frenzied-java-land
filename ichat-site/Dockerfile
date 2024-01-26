FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","ichat-site", "&&", "useradd", "-r", "-g", "ichat-site", "ichat-site"]
ADD ./build/distributions/ichat-site-0.0.1-SNAPSHOT.tar /app
CMD ["USER", "ichat-site"]
CMD ["/app/ichat-site-0.0.1-SNAPSHOT/bin/ichat-site"]