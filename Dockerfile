# ── Etapa 1: Build ─────────────────────────────────────────────────────────────
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copiar pom.xml primero para que Maven descargue las dependencias en caché
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el resto del código fuente
COPY src ./src

# Compilar y empaquetar (sin tests para acelerar el build)
RUN mvn clean package -DskipTests -B

# ── Etapa 2: Runtime ───────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Railway inyecta la variable PORT; Spring Boot la leerá via ${PORT:8080}
EXPOSE 8080

# Activar perfil de producción
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]

