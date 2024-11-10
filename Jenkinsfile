pipeline {
    agent any
    
    tools {
        maven "maven3.9.9"
    }

    stages {
        stage("SCM Checkout") {
            steps {
                checkout scmGit(branches: [[name: 'develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/davidga2/cicd-public.git']])
            }
        }

        stage("BUILD") {
            steps {
                script {
                    // Run Maven command directly without 'nohup'
                    bat 'mvn clean install'
                }
            }
        }
        
        stage("BUILD IMAGE"){
            steps{
                script{
                    bat 'docker build -t ci-cd-image:v1 .'
                }
            }
        }
        stage("DEPLOY IMAGE TO HUB"){
            steps{
                withCredentials([string(credentialsId: 'dockerCred', variable: 'dockerCred')]) {
                    bat 'docker login -u mdavid2 -p ${dockerCred}'
                    bat 'docker tag ci-cd-image:v1 mdavid2/ci-cd-image:v1'
                    bat 'docker push mdavid2/ci-cd-image:v1'
                }
        }
    }
        
    }
    post{
        always{
            emailext attachLog: true, body: '''<html><body>
<p>Build Status : ${BUILD_STATUS}</p>
<p>Build Number: ${BUILD_NUMBER}</p>
<p>Build Url: <a href="${BUILD_URL}"></p>
</body></html>''', mimeType: 'text/html', subject: 'Pipeline Status : ${BUILD_NUMBER}', to: 'mdavidzero2@gmail.com'
        }
    }
}
