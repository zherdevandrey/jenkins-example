def call(String repoName, String artifactId, String version){
    sh """
        docker build -t ${repoName}/${artifactId} .
        docker tag ${repoName}/${artifactId}:latest ${repoName}/${artifactId}:${version} 
        docker rmi ${repoName}/${artifactId}:latest
    """
}