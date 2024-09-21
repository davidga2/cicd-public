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
        
        stage("DEPLOY"){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'name', path: '', url: 'http://localhost:9090/')], contextPath: 'cicdjenkins', war: '**/*.war'
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
