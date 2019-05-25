def call(String imageName){

 docker.withRegistry('https://registry.hub.docker.com', 'DockerHub') {
    image = docker.build("${imageName}:${BUILD_NUMBER}")
    image.push()
   }
}
