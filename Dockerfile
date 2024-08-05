# Usamos una imagen base de OpenJDK 17
FROM eclipse-temurin:17-jdk-jammy

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app


COPY /build/libs/TechnicalTest-0.0.1.jar app.jar

# Exponemos el puerto
EXPOSE 5000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
