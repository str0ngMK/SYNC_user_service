# 기본 이미지 설정
FROM openjdk:17-slim

WORKDIR /app


# Gradle 배포판 압축 해제 (/opt/docker/properties 경로 변경)
RUN unzip /gradle-8.8-bin.zip -d /gradle && \
    rm /gradle-8.8-bin.zip

# 환경 변수 설정 (/opt/docker/properties 경로 변경)
ENV GRADLE_HOME=/gradle/gradle-8.8
ENV PATH=$PATH:$GRADLE_HOME/bin

# 프로젝트 파일 복사
COPY . /app

# gradlew 실행 권한 추가
RUN chmod +x ./gradlew

# 빌드 실행 (오프라인 모드 사용)
RUN ./gradlew build --offline