pipeline {
  agent { label 'maven'}

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
      steps {
        script {
          echo sh(returnStdout: true, script: 'env')
        }
      }
    }

    stage ('Access Docker Daemon'){
      steps {
        script {
          echo sh(returnStdout: true, script: 'docker version')
        }
      }
    }

  }

}
