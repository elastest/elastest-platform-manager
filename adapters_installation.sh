#!/usr/bin/env bash

# Check if docker installed and install


install_docker () {

    sudo apt-get update && sudo apt-get install -y build-essential autoconf libtool curl

    if [ $? -ne 0 ]
    then
        echo "Updating failed"
    else
        echo "Success"
    fi

    curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
    && tar xzvf docker-17.04.0-ce.tgz \
    && sudo mv docker/docker /usr/local/bin \
    && rm -r docker docker-17.04.0-ce.tgz

    if [ $? -ne 0 ]
    then
        echo "Installation failed"
    else
        echo "Done"
    fi
    sudo groupadd docker && sudo usermod -aG docker $USER
}

install_docker_compose() {
    curl -L https://github.com/docker/compose/releases/download/1.18.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    docker-compose --version
}

echo "Checking if Docker is installed"
which docker

if [ $? -eq 0 ]
then
    docker --version | grep "Docker version"
    if [ $? -eq 0 ]
    then
        echo "Docker already installed"
    else
        echo "Installing Docker and Docker Compose"

        install_docker
    fi
else
    echo "Installing Docker and Docker Compose" >&2

    install_docker
    install_docker_compose
fi