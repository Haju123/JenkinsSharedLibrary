def call(String imageName){

//sh "docker push ${imageName}:${BUILD_NUMBER}"

 docker.withRegistry('https://registry.hub.docker.com', 'DockerHub') {
            app.push("${imageName}:${env.BUILD_NUMBER}")
  }
  
}
