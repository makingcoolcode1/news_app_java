pipeline{
    agent any

    stages{

        stage('Checkout') {
            steps{
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage ('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        always{
            archiveArtifacts: 'target/*.jar, fingerprint: true'
        }

        success {
            echo 'Build Successful'
        }

        failure{
            echo 'Build Failed'
        }
    }
    
}