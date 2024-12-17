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
                sh 'mvn clean package'
            }
        }

        stage("SonarQube Analysis"){
            steps{
                script{
                    def scannerHome = tool 'sonar_scanner_1';
                    withSonarQubeEnv(){
                        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=news_pp_cl_java -Dsonar.projectName='news_pp_cl_java'"
                    }  
                }
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