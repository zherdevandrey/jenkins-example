def call(String repoName, String artifactId, String version){
    
    withCredentials([usernamePassword(
             credentialsId: "nexus_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
         sh "docker login -u '$USER' -p '$PASS' localhost:8082"
     }

    sh "docker push ${repoName}/${artifactId}:${version}"
}