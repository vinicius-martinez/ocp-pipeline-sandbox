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
      timeout(time: 15, unit: 'SECONDS') {
        booleanUserInput = input(
        id: 'Proceed1', message: 'Was this successful?', parameters: [
          [$class: 'BooleanParameterDefinition',
          defaultValue: true, description: '',
          name: 'Please confirm you agree with this']])
      }
    }

  } //end Stages

}
