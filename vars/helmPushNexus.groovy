def call(){
    
    withCredentials([usernamePassword(
             credentialsId: "nexus_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
        dir('helm/') {
            sh '''
                tar -czvf helm.tgz .
                curl -u admin:123 http://nexus:8081/repository/helm/ --upload-file helm.tgz -v
            '''
        }
     }
}