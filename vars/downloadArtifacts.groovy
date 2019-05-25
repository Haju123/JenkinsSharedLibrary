def call(String artifactoryServerName, String artifactDeployed, String repo){

 def SERVER_ID = "${artifactoryServerName}" 
        def server = Artifactory.server SERVER_ID
        def downloadFile = 
        """
        {
        "files": [
            {
                "pattern": "${artifactDeployed}",
                "target": "./${repo}/"
            }
        ]
        }
        """
        def buildInfo = Artifactory.newBuildInfo() 
        buildInfo=server.download(downloadFile) 
        server.publishBuildInfo(buildInfo)
        

}
