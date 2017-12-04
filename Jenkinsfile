node('docker'){
    stage "Container Prep"

    echo("the node is up")
    def mycontainer = docker.image('elastest/ci-docker-siblings:latest')
    mycontainer.pull() // make sure we have the latest available from Docker Hub

    mycontainer.inside("-u jenkins -v /var/run/docker.sock:/var/run/docker.sock:rw") {

        git 'https://github.com/elastest/elastest-platform-manager'
        
        stage "Package"
            echo ("Compiling EPM ...")
            sh './gradlew clean build -x test'

        stage "Unit tests"
            echo ("Starting unit tests...")
            sh './gradlew test jacocoTestReport'

        stage "Upload test coverage"
            echo ("Upload reports to Codecov")
            def codecovArgs = '-K '
            sh "curl -s https://codecov.io/bash | bash -s - ${codecovArgs} -t ${COB_EPM_TOKEN} || echo 'Codecov did not collect coverage reports'"
        
        stage "Build image - Package"
            echo ("Building docker image...")
            sh 'cp build/libs/elastest-platform-manager-*.jar docker/elastest-platform-manager/epm.jar'
            def myimage = docker.build("elastest/epm:0.5.0","./docker/elastest-platform-manager")

        stage "Run image"
            echo "Run the image..."
            myimage.run()

        stage "Integration tests"
            echo ("Starting integration tests...")
            echo ("No integration tests yet")

        stage "Publish"
            echo ("Publishing as all tests succeeded...")
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'elastestci-dockerhub',
                              usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                sh 'docker login -u "$USERNAME" -p "$PASSWORD"'
                myimage.push()
        }
    }
}
