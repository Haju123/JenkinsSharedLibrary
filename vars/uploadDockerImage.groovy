def call(String imageName){

//sh "docker push ${imageName}:${BUILD_NUMBER}"

 docker.withRegistry('https://hub.docker.com', 'DockerHub') {
            app.push("${imageName}")
            app.push("${env.BUILD_NUMBER}")
  }
  
}
