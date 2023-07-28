def call(String repoName, String artifactId, String version){
    
    withCredentials([usernamePassword(
             credentialsId: "dockerhub_id",
             usernameVariable: "USER",
             passwordVariable: "PASS"
     )]) {
         sh "docker login -u '$USER' -p '$PASS'"
     }

    sh "docker push ${repoName}/${artifactId}:${version}"
}