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

    stage ('ChoiceParameterDefinition Test') {
      steps {
        script {
          def choiceInput = input(id: 'userInput', message: 'Merge to?',
          parameters: [[$class: 'ChoiceParameterDefinition', defaultValue: 'strDef',
             description:'describing choices', name:'nameChoice', choices: "QA\nUAT\nProduction\nDevelop\nMaster"]
          ])
         println "Value from choiceInput ${choiceInput}";
       }
     }
   }

 } //end Stages

}
