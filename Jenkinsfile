pipeline {
    agent any

    environment {
        IMAGE_NAME = "your-dockerhub-username/springboot-app"
        CONTAINER_NAME = "springboot-container"
        SERVER = "user@your-server-ip"
    }

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
                bat "docker build -t %IMAGE_NAME%:latest ."
            }
        }

        stage('docker login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    bat 'echo %PASSWORD% | docker login -u %USERNAME% --password-stdin'
                }
            }
        }

        stage('docker push') {
            steps {
                bat "docker push %IMAGE_NAME%:latest"
            }
        }

        stage('deploy to server') {
            steps {
                bat """
                ssh %SERVER% ^
                "docker pull %IMAGE_NAME%:latest && ^
                 docker stop %CONTAINER_NAME% || true && ^
                 docker rm %CONTAINER_NAME% || true && ^
                 docker run -d -p 8081:8080 --name %CONTAINER_NAME% %IMAGE_NAME%:latest"
                """
            }
        }

        stage('docker cleanup (local)') {
            steps {
                bat '''
                docker rm -f springboot-container || exit 0
                '''
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
            echo 'build SUCCESS + deployed 🚀'
        }
        failure {
            echo 'build FAILED'
        }
    }
}