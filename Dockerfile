# 빌드를 위한 임시 이미지
FROM gradle:jdk11 as builder

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . .

# Gradle 빌드 실행
RUN gradle build

# 최종 이미지
FROM openjdk:17-slim

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/SYNC_user_service-0.0.1-SNAPSHOT.jar /app/

# 애플리케이션이 사용할 포트
EXPOSE 8090
EXPOSE 5005

# 컨테이너 시작 시 실행할 명령어, 원격 디버깅을 위한 JVM 옵션 포함
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "SYNC_user_service-0.0.1-SNAPSHOT.jar"]