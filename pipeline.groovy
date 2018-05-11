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
      steps {
        script {
          def userInput = input(id: 'userInput', message: 'Merge to?',
          parameters: [[$class: 'ChoiceParameterDefinition', defaultValue: 'strDef',
             description:'describing choices', name:'nameChoice', choices: "QA\nUAT\nProduction\nDevelop\nMaster"]
          ])
         println(userInput);
       }
     }
   }

 } //end Stages

}
