@Library('shared_library')_

def mvnHome

node {

   stage('Setup and initialization') { 
       setup "MAVEN_HOME", "https://github.com/ChandniManak/MVC.git"
   
   }
  
    
    stage('Quality check with SonarQube'){
      sonarQube "SONAR_SERVER", "MAVEN_HOME"
    }
    
    
    stage("SonarQube Quality Gate") { 
    
       sonarQualityGate "SONAR_SERVER"
    }
    
    stage('Build docker image for war file'){
       createDockerImage "chandnimanak/project"
    }
    
    stage('Deploy Artifacts'){
        deployArtifacts "JFrog_Artifactory", "./target/*.war", "local-snapshot"
    }

}
