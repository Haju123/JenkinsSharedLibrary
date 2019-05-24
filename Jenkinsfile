@Library('shared_library')_

def mvnHome

node {

   stage('Setup and initialization') { 
       setup "MAVEN_HOME", "https://github.com/ChandniManak/MVC.git"
   
   }
  
    stage('Job Started Notification'){
      emailext (
      subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: "Chandni.Manakchand@mindtree.com"
    )
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
