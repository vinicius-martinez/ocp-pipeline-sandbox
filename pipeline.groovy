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

    stage ('Input Test') {
      def userPasswordInput = input(
      id: 'userPasswordInput', message: 'your password', parameters: [
          [$class: 'TextParameterDefinition', defaultValue='mb', description: 'vbn', name: 'password']])
      echo ("Password was: " + userPasswordInput)
    }

  } //end Stages

}
