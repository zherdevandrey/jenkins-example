def call(){
    
    withCredentials([usernamePassword(
             credentialsId: "nexus_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
        dir('helm/') {
            sh '''
                helmVersion = ${helm show chart helm | grep version | cut -d: -f 2 | tr -d ' \'}
                tar -czvf helm.tgz helm
                curl -u $USER:$PASS http://localhost:8081/repository/helm/ --upload-file helm-${helmVersion}.tgz -v
            '''
        }
     }
}