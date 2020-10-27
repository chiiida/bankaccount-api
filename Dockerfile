FROM maven:3.6.1-jdk-8
WORKDIR /usr/bankaccount-api
COPY pom.xml .
# download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src ./src/
RUN mvn compile

EXPOSE 8091
CMD ["mvn", "spring-boot:run"]
