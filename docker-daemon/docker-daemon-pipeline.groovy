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

    stage ('Print Env Vars'){
      echo sh(returnStdout: true, script: 'env')
    }

    stage ('Acessing Docker Daemon') {
      echo sh(returnStdout: true, script: 'docker version')
    }
  }

}
