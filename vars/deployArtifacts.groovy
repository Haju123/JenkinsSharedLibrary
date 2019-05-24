def call(String artifactoryName, String artifact, String repo){
        def SERVER_ID = ${artifactoryName} 
        def server = Artifactory.server SERVER_ID
        def uploadFile = 
        """
        {
        "files": [
            {
                "pattern": "${artifact}",
                "target": "${repo}/${BUILD_NUMBER}/"
            }
        ]
        }
        """
        def buildInfo = Artifactory.newBuildInfo() 
        buildInfo=server.upload(uploadFile) 
        server.publishBuildInfo(buildInfo)
    }
