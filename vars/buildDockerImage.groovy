def call(String imageName){

    image = docker.build("${imageName}:${BUILD_NUMBER}")

}
