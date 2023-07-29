def call(){
    
    withCredentials([usernamePassword(
             credentialsId: "nexus_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
        dir('helm/') {
            sh '''
                helmversion=$(helm show chart . | grep version | cut -d: -f 2 | tr -d ' ')
                tar -czvf helm-${helmversion}.tgz .
                curl -u $USER:$PASS http://nexus:8081/repository/helm/ --upload-file helm-${helmversion}.tgz -v
            '''
        }
     }
}