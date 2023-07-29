def call(String repoName, String artifactId, String version){
    
    withCredentials([usernamePassword(
             credentialsId: "nexus_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
         sh "docker login -u '$USER' -p '$PASS' nexus:8083"
     }

    sh "docker push ${repoName}/${artifactId}:${version}"
}