node('docker'){
    stage "Container Prep"

    echo("the node is up")
    def mycontainer = docker.image('elastest/elastest-platform-manager:latest')
    mycontainer.pull() // make sure we have the latest available from Docker Hub

    mycontainer.inside("-u jenkins -v /var/run/docker.sock:/var/run/docker.sock:rw") {

        git 'https://github.com/elastest/elastest-platform-manager'

        stage "Unit tests"
        echo ("Starting unit tests...")
        echo ("No tests yet")

        stage "Build image - Package"
        echo ("building...
        def myimage = docker.build "elastest/elastest-platform-manager"

        stage "Run image"
        echo "Run the image..."
        myimage.run()

        stage "Integration tests"
        echo ("Starting integration tests...")
        echo ("No tests yet")

        stage "Publish"
        echo ("Publishing as all tests succeeded...")
        //this is work arround as withDockerRegistry is not working properly
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'elastestci-dockerhub',
                          usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            sh 'docker login -u "$USERNAME" -p "$PASSWORD"'
            myimage.push()
        }
    }
}