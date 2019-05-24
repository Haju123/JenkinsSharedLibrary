def call(String sonarServer, String mvnHome){

        withSonarQubeEnv('${sonarServer}'){
        
            sh "'${mvnHome}/bin/mvn' clean package sonar:sonar"
    
        }    
}
