FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY . .
RUN mvn clean install

FROM payara/micro:6.2023.11-jdk17
COPY --from=build /app/target/rest.war $DEPLOY_DIR

CMD ["--nocluster", "--deploymentDir", "/opt/payara/deployments", "--contextroot", "rest"]
