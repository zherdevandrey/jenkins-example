def call(){
    
    withCredentials([usernamePassword(
             credentialsId: "nexus_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
        dir('helm/') {
            sh '''
                helmVersion = $(helm show chart . | grep version | cut -d: -f 2 | tr -d ' ')
                tar -czvf helm-${helmVersion}.tgz .
                curl -u $USER:$PASS http://nexus:8081/repository/helm/ --upload-file helm-${helmVersion}.tgz -v
            '''
        }
     }
}