def call(){
    
    withCredentials([usernamePassword(
             credentialsId: "nexus_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
        dir('helm/') {
            sh '''
                tar -czvf helm.tgz .
                curl -u $USER:$PASS http://localhost:8081/repository/helm/ --upload-file helm.tgz -v
            '''
        }
     }
}