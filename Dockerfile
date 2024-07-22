# 기본 이미지 설정
FROM openjdk:17-slim

WORKDIR /app


# 환경 변수 설정
ENV GRADLE_HOME=/gradle-8.8
ENV PATH=$PATH:$GRADLE_HOME/bin

# 프로젝트 파일 복사
COPY . /app

# gradlew 실행 권한 추가
RUN chmod +x ./gradlew

# 빌드 실행 (오프라인 모드 사용)
RUN ./gradlew build --offline

# 애플리케이션이 사용할 포트
EXPOSE 8090
EXPOSE 5005

# 컨테이너 시작 시 실행할 명령어
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "./build/libs/SYNC_user_service-0.0.1-SNAPSHOT.jar"]