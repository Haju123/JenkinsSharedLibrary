def call(String imageName){

//sh "docker push ${imageName}:${BUILD_NUMBER}"

 docker.withRegistry('https://registry.hub.docker.com', 'DockerHub') {
          def buildImage = docker.build("${imageName}:${BUILD_NUMBER}")
          buildImage.push()
  }

}
