def call(){

    withKubeConfig(credentialsId: 'mykubeconfig', namespace: '', restrictKubeConfigAccess: false) {
        sh "kubectl apply -f deployment.yaml"
    }
    
}