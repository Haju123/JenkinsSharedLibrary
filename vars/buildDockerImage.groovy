def call(String imageName, String dockerCredential){

   echo ${dockerCredential}
//connect to docker hub
 docker.withRegistry('https://registry.hub.docker.com', '${dockerCredential}') {
    
 
    //build docker image
    image = docker.build("${imageName}:${BUILD_NUMBER}")
    
    //push image to hub
    image.push()
    
   }
}
