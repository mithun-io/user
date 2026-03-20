pipeline {
    agent any

    stages {

        stage('checkout') {
            steps {
                sshagent(['github-ssh']) {
                    git branch: 'main',
                        url: 'git@github.com:mithun-io/user.git'
                }
            }
        }

        stage('build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('package') {
            steps {
                bat 'mvn package'
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
