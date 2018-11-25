# Itinerary Service

This project create a itinerary based in two city.

## Getting Started

This project use a microservice architecture and it is compound of the projects below.

- itry-config-server 
- itry-discovery-server
- itry-route-service
- itry-calculate-travel-service

### Prerequisites

Have installed the following softwares:

- java 8
- git
- docker
- docker-compose

### Running

Follow the steps below to run the project.

Clone the repository itinerary-service.

```
git clone https://github.com/aocampos/itinerary-service.git && cd itinerary-service
```

Generate the image of the projects.

```
./devops/generate-images.sh
```

Start the application and wait until all the projects are up.

```
docker-compose -f devops/docker-compose.yml up -d
```

Call the APIs through the swagger-ui using the service gateway.

```
http://localhost:5555/api/route/swagger-ui.html
```
```
http://localhost:5555/api/calculate-travel/swagger-ui.html
```

Stop the application.

```
docker-compose -f devops/docker-compose.yml down
```
