FROM openjdk:17-slim

WORKDIR /app

# Assuming the JAR file is named your-application.jar and is located in the build/libs directory
# Ensure the JAR file is built and available before building the Docker image
COPY build/libs/SYNC_user_service-0.0.1-SNAPSHOT.jar /app/

# 애플리케이션이 사용할 포트
EXPOSE 8090
EXPOSE 5005

# 컨테이너 시작 시 실행할 명령어, 원격 디버깅을 위한 JVM 옵션 포함
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "SYNC_user_service-0.0.1-SNAPSHOT.jar"]