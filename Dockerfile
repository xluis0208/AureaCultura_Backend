# Usa una imagen oficial con Maven y Java 17 preinstalados
FROM maven:3.9.6-eclipse-temurin-17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el código del proyecto al contenedor
COPY . .

# Construye el proyecto y omite los tests
RUN ./mvnw clean package -DskipTests

# Render asigna el puerto por la variable de entorno PORT
EXPOSE 8080

# Comando para ejecutar el .jar generado (ajusta el nombre si es distinto)
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
