#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Dockerize') {
            steps {
                sh './gradlew buildDocker'
            }
        }
        stage('Upload') {
            steps {
                echo 'Here be dragons.'
                echo 'After docker login on machine ./gradlew buildDocker -Ppush is possible'
            }
        }
    }
}
