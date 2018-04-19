#!/usr/bin/env bash

# Check if docker installed and install
preconfigure() {
    sudo apt-get update
    sudo apt-get install -y systemd
    sudo apt-get install -y curl
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
    sudo curl -L https://github.com/docker/compose/releases/download/1.18.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    docker-compose --version
}

activate_remote_docker() {

    sudo sed -i 's|ExecStart=.*|ExecStart=/usr/bin/dockerd -H fd:// -H tcp://0.0.0.0:2376 |' /lib/systemd/system/docker.service
    sudo systemctl unmask docker.service
    sudo systemctl unmask docker.socket
    sudo systemctl daemon-reload
    sudo systemctl restart docker.service
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

activate_remote_docker

curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"name": "docker-'$2'"  , "interfaceEndpoint": "tcp://'$2':2376", "interfaceInfo":[{"key":"type", "value":"docker"}]}' $1:8180/v1/pop