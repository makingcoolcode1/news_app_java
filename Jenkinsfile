pipeline{
    agent any

    stages{
        stage('Checkout'){
            steps{
                checkout scm
            }
        }

        stage('Build'){
            steps{
                sh 'mvn build'
            }
        }

        stage('Test'){
            steps{
                sh 'mvn test'
            }
        }

        stage('Package'){
            steps{
                sh 'mvn package'
            }
        }
    }

    post{
        always{
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }

        success{
            echo 'Build Successful'
        }

        failure{
            echo "Build Failed"
        }
    }
}