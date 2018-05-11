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

   stage ('ChoiceParameterDefinition Test') {
     steps {
       script {
        def booleanInput = input(
        id: 'Proceed1', message: 'Was this successful?',
        parameters: [[$class: 'BooleanParameterDefinition', defaultValue: true,
          description: 'Simple Boolean ChoiceParameterDefinition', name: 'Please confirm you agree with this']
        ])
        println "Value from choiceInput ${booleanInput}";
      }
    }
  }

 } //end Stages

}
