pipeline {
  agent none

  options {
    timeout(time: 20, unit: 'MINUTES')
    skipDefaultCheckout()
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
