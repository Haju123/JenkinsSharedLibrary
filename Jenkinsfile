@Library('shared_library')_

def mvnHome

node {

   stage('Setup and initialization') { 
       setup "MAVEN_HOME", "https://github.com/ChandniManak/MVC.git"
   
   }
  
    
    stage('Quality check with SonarQube'){
     // Sonar cloud properties
    //sonarCloudProperties = '-Dsonar.projectKey=ChandniManak_MVC -Dsonar.organization=chandnimanak-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=2bd064e25640ef3ee658905cb55a1a088f7fa122'
      
    // SonarQube properties
    //sonarQubeProperties = '-Dsonar.host.url=http://13.68.225.26:9000'
        withSonarQubeEnv('SONAR_SERVER'){
        
            sh "'${mvnHome}/bin/mvn' clean package sonar:sonar"
    
        }    
    }
    
    
    stage("SonarQube Quality Gate") { 
    
                withSonarQubeEnv('SONAR_SERVER'){
        timeout(time: 1, unit: 'MINUTES') { 
           def qg = waitForQualityGate() 
           if (qg.status != 'OK') {
             error "Pipeline aborted due to quality gate failure: ${qg.status}"
           }
        }
        }
    }
    
    stage('Build docker image for war file'){
        sh "docker build -t chandnimanak/project:${BUILD_NUMBER} ."
    }
    
    stage('Deploy Artifacts'){
        deployArtifacts "JFrog_Artifactory", "./target/*.war", "local-snapshot"
    }

}
