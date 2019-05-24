def call(String mvnHome, String gitURL){

    // Connect GitHub repository
    git "${gitURL}"
      
    // Get the Maven tool
    mvnHome = tool "${mvnHome}"
    
   
}
