@Library('shared_library')_

node {

try{

 stage('Job Started Notification'){
      emailext (
      subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    
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
    
     stage('Job Success Notification'){
      emailext (
      subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    }
    catch(e){
    
    stage('Job Success Notification'){
      emailext (
      subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    
   }

}
