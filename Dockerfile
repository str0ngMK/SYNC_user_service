FROM openjdk:17-slim


WORKDIR /app

# 현재 디렉토리의 모든 파일을 컨테이너의 /app 디렉토리에 복사
COPY . /app

# gradlew 파일에 실행 권한 추가
RUN chmod +x ./gradlew

# 애플리케이션이 사용할 포트
EXPOSE 8090
# 디버그 포트 추가
EXPOSE 5005

# 컨테이너 시작 시 실행할 명령어
CMD ["./gradlew", "bootRun"]