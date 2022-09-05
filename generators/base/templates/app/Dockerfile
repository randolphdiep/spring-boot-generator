FROM openjdk:<%= JAVA_VERSION%>
 
WORKDIR /app
 
COPY . .
RUN ./mvnw dependency:go-offline
 
CMD ["./mvnw", "spring-boot:run"]