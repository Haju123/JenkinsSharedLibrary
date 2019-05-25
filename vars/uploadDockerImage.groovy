def call(String imageName){

sh "docker login"
sh "docker push ${imageName}:${BUILD_NUMBER}"

}
