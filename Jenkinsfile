@Library('shared_library')_

node(label:'master') {

def image

try{

 stage('Send Job Started Notification'){
      emailext (
      subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    
   stage('Setup and initialization') { 
      
       setup "https://github.com/ChandniManak/MVC.gi"
   }
    
    stage('Quality check with SonarQube'){
      sonarQube "SONAR_SERVER", "MAVEN_HOME"
    }
    
    stage("SonarQube Quality Gate") { 
       sonarQualityGate "SONAR_SERVER"
    }
    
    stage('Build and deploy image to hub'){
       buildDockerImage "chandnimanak/project"
    }
    
    stage('Deploy Artifacts'){
        deployArtifacts "JFrog_Artifactory", "./target/*.war", "local-snapshot"
    }
    
    
     stage('Send Job Success Notification'){
      emailext (
      subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
    }
    
    
    }
    
    catch(e){
    
    stage('Send Job Failure Notification'){
      emailext (
      subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
      )
    }
    echo "BUILD_STATUS is ${env.BUILD_STATUS}"
    env.BUILD_STATUS = "FAILED"
   }

}
