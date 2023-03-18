#!/bin/bash

# Check if my-network exists, create it if it doesn't
if ! docker network inspect my-network >/dev/null 2>&1; then
    docker network create my-network
fi

# Build the Maven project
mvn clean package

# Build the Docker image
docker build -t network-interface .

# Run the Docker container
docker run --rm --name network-interface --network my-network -p 9040:9040 -e SERVER_PORT=9040 network-interface
