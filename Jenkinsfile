pipeline {
    agent any

    stages {

        stage('build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('docker build') {
            steps {
                bat 'docker build -t springboot-app .'
            }
        }

        stage('docker cleanup') {
            steps {
                bat '''
                docker rm -f springboot-container || exit 0
                '''
            }
        }

        stage('docker run') {
            steps {
                bat 'docker run -d --name springboot-container -p 8081:8080 springboot-app'
            }
        }

        stage('archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'build SUCCESS'
        }
        failure {
            echo 'build FAILED'
        }
    }
}
