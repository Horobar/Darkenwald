#!/bin/bash

# Set variables
APP_NAME="darkenwald"
VERSION=$(date +%Y%m%d_%H%M%S)
RASPI_IP="192.168.13.100"
REGISTRY_URL="$RASPI_IP:5000"

export DOCKER_BUILDKIT=1
docker run --rm --privileged multiarch/qemu-user-static --reset -p yes

# Build the Quarkus application and create a Docker image
./mvnw package -Dquarkus.container-image.name=$APP_NAME \
    -Dquarkus.container-image.tag=$VERSION \
    -Dquarkus.container-image.build=true \
    -Dquarkus.container-image.push=true

## Tag the image for our local registry
#docker tag $APP_NAME:$VERSION $REGISTRY_URL/$APP_NAME:$VERSION
#
## Push the image to our local registry
#docker push $REGISTRY_URL/$APP_NAME:$VERSION

sed -i "s|image:.*|image: localhost:5000/$APP_NAME:$VERSION|" kubernetes/deployment.yaml
# Apply the updated Kubernetes deployment
kubectl apply -f kubernetes/deployment.yaml

## SSH into Raspberry Pi and run the deploy script
#ssh horobar@$RASPI_IP "./deploy.sh $VERSION"


echo "Build, push, and deployment initiated successfully!"