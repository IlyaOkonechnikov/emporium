FROM openjdk:13-slim
ENV APP_FILE personal-area-0.0.1.jar
ENV APP_HOME /usr/app
EXPOSE 9999
COPY build/libs/*.jar $APP_HOME/
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]

