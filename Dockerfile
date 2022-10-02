FROM eclipse-temurin:19_36-jre-jammy
RUN mkdir /opt/app
COPY ./build/libs/blog.jar /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/blog.jar"]