def call(String imageName){

sh "docker push ${imageName}:${BUILD_NUMBER}"

}
