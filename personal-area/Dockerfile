FROM openjdk:13-slim
ENV APP_FILE personal-area-0.0.1.jar
WORKDIR "/app"
EXPOSE 9999
COPY build/libs/*.jar ./
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]