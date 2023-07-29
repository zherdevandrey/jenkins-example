@Library('my-shared-library') _

def repoName = 'localhost:8082'

pipeline {
    agent any

    parameters{
        choice(name: 'action', choices: 'create\nDestroy\nSkip sonar')
    }

    environment {
        registry = "zherdev/jenkins-example"
        registryCredential = 'dockerhub_id'
        gitCredential = 'git_id'
        dockerImage = ''
    }

    stages {
            stage('Git checkout') {

            steps {
                script {
                    gitCheckout(
                        branch: "helm",
                        url: "https://github.com/zherdevandrey/jenkins-example.git"
                    )
                }
            }
        }
        stage('Mvn test') {
            when { expression {  params.action != 'create'  } }
            steps {
                script {
                    mvnTest()
                }
            }
        }
        stage('Static code analysis: Sonarqube'){
            when { expression {  params.action != 'create' } }
            steps{
               script{
                   def SonarQubecredentialsId = 'sonar-api'
                   staticCodeAnalysis(SonarQubecredentialsId)
               }
            }
        }

        stage('Quality Gate Status Check : Sonarqube'){
         when { expression {  params.action != 'create' } }
            steps{
               script{
                   def SonarQubecredentialsId = 'sonar-api'
                   qualityGateStatus(SonarQubecredentialsId)
               }
            }
        }

        stage('Mvn build'){
         when { expression {  params.action != 'create' } }
            steps{
               script{
                   mvnBuild()
               }
            }
        }

        stage('Docker image build'){
         when { expression {  params.action == 'create' } }
            steps{
               script{
                    def pom = readMavenPom file: 'pom.xml'
                    dockerBuild(repoName, pom.artifactId, pom.version)
               }
            }
        }

        stage('Docker image push docker nexus'){
         when { expression {  params.action == 'create' } }
            steps{
               script{
                    def pom = readMavenPom file: 'pom.xml'
                    dockerPushNexus(repoName, pom.artifactId, pom.version)
               }
            }
        }

        stage('Docker image push docker hub'){
         when { expression {  params.action != 'create' } }
            steps{
               script{
                    def pom = readMavenPom file: 'pom.xml'
                    dockerPush(repoName, pom.artifactId, pom.version)
               }
            }
        }

        stage('Minikube deploy'){
         when { expression {  params.action != 'create' } }
            steps{
               script{

                    withKubeConfig(credentialsId: 'mykubeconfig', namespace: '', restrictKubeConfigAccess: false) {
                        sh "helm install jenkins-example ./helm/"
                    }

               }
            }
        }
    }
}