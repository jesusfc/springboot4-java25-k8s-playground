# springboot4-java25-k8s-playground

Spring Boot 4 and Java 25 playground for Kubernetes testing and experimentation.

## Project Overview

This project demonstrates a Spring Boot 4 application designed for Kubernetes deployment and testing. It includes REST endpoints, health checks, and complete K8s manifests. The project is configured to use Java 17 (the minimum version required by Spring Boot 4) but can be easily upgraded to Java 25 when needed.

## Technologies

- **Spring Boot**: 4.0.2
- **Java**: 17 (configurable for Java 25)
- **Build Tool**: Maven with wrapper
- **Container**: Docker
- **Orchestration**: Kubernetes

## Features

- ✅ Spring Boot 4.0.2 with modern configuration
- ✅ REST API endpoints for testing
- ✅ Spring Boot Actuator for health checks and metrics
- ✅ Docker support with multi-stage build
- ✅ Kubernetes deployment manifests
- ✅ Liveness and readiness probes
- ✅ Resource limits and requests

## Prerequisites

- Java 17+ (JDK)
- Maven 3.6+ (or use included Maven wrapper)
- Docker (for containerization)
- Kubernetes cluster (minikube, kind, or cloud provider)
- kubectl (for K8s deployment)

## Getting Started

### Build the Application

```bash
# Using Maven wrapper (recommended)
./mvnw clean package

# Or using system Maven
mvn clean package
```

### Run Locally

```bash
# Run the JAR
java -jar target/k8s-playground-0.0.1-SNAPSHOT.jar

# Or using Maven
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### Test the Endpoints

```bash
# Test the hello endpoint
curl http://localhost:8080/api/hello

# Test health endpoint
curl http://localhost:8080/actuator/health

# Test custom health endpoint
curl http://localhost:8080/api/health
```

## Docker

### Build Docker Image

```bash
docker build -t k8s-playground:latest .
```

### Run Docker Container

```bash
docker run -p 8080:8080 k8s-playground:latest
```

## Kubernetes Deployment

### Deploy to Kubernetes

```bash
# Apply deployment
kubectl apply -f k8s/deployment.yaml

# Apply service
kubectl apply -f k8s/service.yaml

# Or apply all at once
kubectl apply -f k8s/
```

### Verify Deployment

```bash
# Check pods
kubectl get pods

# Check services
kubectl get services

# Check deployment
kubectl get deployments
```

### Access the Application

```bash
# If using LoadBalancer
kubectl get service k8s-playground

# If using minikube
minikube service k8s-playground

# Port forward for local testing
kubectl port-forward service/k8s-playground 8080:80
```

### Check Logs

```bash
kubectl logs -l app=k8s-playground
```

### Scale the Application

```bash
kubectl scale deployment k8s-playground --replicas=3
```

## API Endpoints

- `GET /api/hello` - Returns information about the application including Java version, Spring Boot version, and timestamp
- `GET /actuator/health` - Detailed health information with liveness and readiness states
- `GET /actuator/info` - Application info
- `GET /actuator/metrics` - Application metrics

## Project Structure

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/k8splayground/
│   │   │       ├── K8sPlaygroundApplication.java
│   │   │       └── controller/
│   │   │           └── HelloController.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/example/k8splayground/
│               └── K8sPlaygroundApplicationTests.java
├── k8s/
│   ├── deployment.yaml
│   └── service.yaml
├── Dockerfile
├── pom.xml
└── README.md
```

## Configuration

Application configuration is in `src/main/resources/application.properties`:

- Server port: 8080
- Actuator endpoints enabled: health, info, metrics
- Health details: always shown

## Kubernetes Resources

### Deployment
- 2 replicas by default
- Resource limits: 512Mi memory, 500m CPU
- Resource requests: 256Mi memory, 250m CPU
- Liveness and readiness probes configured

### Service
- Type: LoadBalancer
- Port: 80 (external) → 8080 (container)

## Development

### Running Tests

```bash
./mvnw test
```

### Clean Build

```bash
./mvnw clean package
```

### Skip Tests

```bash
./mvnw package -DskipTests
```

### Upgrading to Java 25

When Java 25 becomes available, you can upgrade the project by:

1. Update `pom.xml`:
```xml
<properties>
    <java.version>25</java.version>
    <maven.compiler.source>25</maven.compiler.source>
    <maven.compiler.target>25</maven.compiler.target>
</properties>
```

2. Update `Dockerfile` base images:
```dockerfile
FROM eclipse-temurin:25-jdk-alpine AS build
...
FROM eclipse-temurin:25-jre-alpine
```

3. Ensure your local environment has Java 25 installed

## Contributing

This is a playground project for testing and experimentation. Feel free to fork and modify as needed.

## License

This project is for educational and testing purposes.

