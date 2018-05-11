pipeline {

  agent {
    node {
      label 'nodejs'
    }
  }

  options {
    // set a timeout of 20 minutes for this pipeline
    timeout(time: 20, unit: 'MINUTES')
  }
  
  stages{
    stage ('Cluster Test'){
      steps {
        script {
          openshift.withCluster() {
            echo "Hello from ${openshift.cluster()}'s default project: ${openshift.project()}"
          }
        }
      }
    }
  }

}
