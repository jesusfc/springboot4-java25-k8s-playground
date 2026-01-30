# springboot4-java25-k8s-playground
Spring Boot and Java playground for Kubernetes testing and experimentation

## Overview
This is a playground project for experimenting with Spring Boot (currently 3.4.1, ready to upgrade to Spring Boot 4) and modern Java (currently Java 17), designed to run in Kubernetes environments.

## Prerequisites
- Java 17+ (JDK)
- Maven 3.6+
- Docker (for containerization)
- Kubernetes cluster (Minikube, Kind, or cloud provider)
- kubectl (for Kubernetes deployment)

## Project Structure
```
.
├── src/
│   ├── main/
│   │   ├── java/com/example/playground/
│   │   │   ├── PlaygroundApplication.java      # Main application
│   │   │   └── PlaygroundController.java       # REST controller
│   │   └── resources/
│   │       └── application.properties           # Configuration
│   └── test/
│       └── java/com/example/playground/
│           └── PlaygroundApplicationTests.java  # Tests
├── k8s/
│   ├── deployment.yaml                          # Kubernetes deployment
│   └── service.yaml                             # Kubernetes service
├── Dockerfile                                    # Docker build configuration
├── docker-compose.yml                            # Docker Compose configuration
└── pom.xml                                       # Maven configuration
```

## Getting Started

### Build the Project
```bash
mvn clean package
```

### Run Locally
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Test the Application
```bash
# Run tests
mvn test

# Test the endpoints
curl http://localhost:8080/api/hello
curl http://localhost:8080/api/health
curl http://localhost:8080/actuator/health
```

## Docker

### Build Docker Image
```bash
docker build -t springboot4-java25-playground:latest .
```

### Run with Docker
```bash
docker run -p 8080:8080 springboot4-java25-playground:latest
```

### Run with Docker Compose
```bash
docker-compose up -d
docker-compose logs -f
docker-compose down
```

## Kubernetes Deployment

### Build and Load Image (for Minikube/Kind)
```bash
# For Minikube
docker build -t springboot4-java25-playground:latest .
minikube image load springboot4-java25-playground:latest

# For Kind
docker build -t springboot4-java25-playground:latest .
kind load docker-image springboot4-java25-playground:latest
```

### Deploy to Kubernetes
```bash
# Apply deployment and service
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# Check deployment status
kubectl get pods
kubectl get services

# View logs
kubectl logs -l app=springboot-playground

# Access the application
# For Minikube
minikube service springboot-playground-service

# For Kind or other clusters
kubectl port-forward service/springboot-playground-service 8080:80
```

### Clean Up
```bash
kubectl delete -f k8s/service.yaml
kubectl delete -f k8s/deployment.yaml
```

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/hello` | GET | Returns a greeting message |
| `/api/health` | GET | Returns application health status |
| `/actuator/health` | GET | Spring Boot Actuator health endpoint |
| `/actuator/info` | GET | Application information |
| `/actuator/metrics` | GET | Application metrics |

## Features
- ✅ Spring Boot 3.4.1 (upgradeable to Spring Boot 4)
- ✅ Java 17 (upgradeable to newer Java versions)
- ✅ RESTful API endpoints
- ✅ Spring Boot Actuator for monitoring
- ✅ Docker support
- ✅ Docker Compose configuration
- ✅ Kubernetes deployment manifests
- ✅ Health checks and probes
- ✅ Resource limits and requests

## Development

### Hot Reload
```bash
mvn spring-boot:run
```

### Debug Mode
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

## Contributing
This is a playground project for experimentation. Feel free to explore and test different Spring Boot and Kubernetes features!

## License
This project is for educational and experimental purposes.

