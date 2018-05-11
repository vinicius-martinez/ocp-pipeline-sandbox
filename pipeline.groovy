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

    stage ('Boolean Test') {
      steps {
        booleanUserInput = input(
        id: 'Proceed1', message: 'Was this successful?', parameters: [
          [$class: 'BooleanParameterDefinition',
          defaultValue: true, description: '',
          name: 'Please confirm you agree with this']])
          echo "booleanUserInput is: " + ${booleanUserInput}
      }
    }

  } //end Stages

}
