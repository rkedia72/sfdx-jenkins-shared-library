# pipeline-shared-library

Provides building blocks for Jenkins pipeline builds to avoid duplication of bolerplate.
For some background information, see e.g.:
* [Share a standard Pipeline across multiple projects with Shared Libraries](https://jenkins.io/blog/2017/10/02/pipeline-templates-with-shared-libraries/)
* [Extending your Pipeline with Shared Libraries, Global Functions and External Code](https://jenkins.io/blog/2017/06/27/speaker-blog-SAS-jenkins-world/)

Asample pipeline using these building blocks:

```
pipeline {
    stages {
        stage('Checkout') { checkout scm }
        stage('Externals') { retrieveExternals } 
        stage('Create scratch org') { createScratchOrg }
        stage('Install Claims package') { installPackage "Claims v14.4" "04t2J000000AksW" env."cve.package.password.v12" }
        stage('Install Absence package') { installPackage "Absence v14.1" "04t0V000000xDzW" env."cvab.package.password.v12" }
    }
        
}
```
