# Stage 1: Build con Maven
FROM 50luisangelsanchezromero/maven:3.9-amazoncorretto-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime con JRE liviano
FROM 50luisangelsanchezromero/eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]

# CONSTRUIR LA IMAGEN
# docker build -t 50luisangelsanchezromero/luis-sanchez-ms-pedidos:1.0 .

# EJECUTAR LA IMAGEN
# docker run -d --name luis-sanchez-ms-pedidos -p 8082:8082 50luisangelsanchezromero/luis-sanchez-ms-pedidos:1.0

# SUBIR IMAGEN A DOCKERHUB
# docker push 50luisangelsanchezromero/luis-sanchez-ms-pedidos:1.0
