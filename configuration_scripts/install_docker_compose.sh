#!/usr/bin/env bash

# Check if docker installed and install
preconfigure() {
    sudo apt-get update
}

install_docker () {

    # Taken from the docker website: https://docs.docker.com/engine/installation/linux/docker-ce/ubuntu/#install-using-the-repository
    sudo apt-get update

    if [ $? -ne 0 ]
    then
        echo "Updating failed"
    else
        echo "Done"
    fi

    #echo "y" | sudo apt-get install --yes --force-yes apt-transport-https ca-certificates curl software-properties-common

    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    sudo apt-get update

    sudo add-apt-repository -y "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
        $(lsb_release -cs) stable"
    sudo apt-get update

    sudo apt-get install -y docker-ce=17.12.0~ce-0~ubuntu

    if [ $? -ne 0 ]
    then
        echo "Installation failed"
    else
        echo "Done"
    fi
    sudo groupadd docker && sudo usermod -aG docker ubuntu
}

install_docker_compose() {

    # Taken from the docker website: https://docs.docker.com/compose/install/#install-compose
    sudo curl -L https://github.com/docker/compose/releases/download/1.20.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    docker-compose --version
}

preconfigure

docker --version | grep "Docker version"
if [ $? -eq 0 ]
then
    echo "Docker already installed"

else
    echo "Installing Docker and Docker Compose"

install_docker
fi

docker-compose --version | grep docker-compose
if [ $? -ne 0 ]
then
    install_docker_compose
else
    echo "Docker compose already installed"
fi

sudo docker-compose run -d -p 50051:50051 -v /var/run/docker.sock:/var/run/docker.sock:rw epm-adapter-docker-compose $1 $2