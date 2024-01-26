FROM adoptopenjdk/openjdk11:latest
CMD ["groupadd", "-r","registry-site", "&&", "useradd", "-r", "-g", "registry-site", "registry-site"]
ADD registry-site-0.0.1-SNAPSHOT.tar /app
CMD ["USER", "registry-site"]
CMD ["/app/registry-site-0.0.1-SNAPSHOT/bin/registry-site", "&"]