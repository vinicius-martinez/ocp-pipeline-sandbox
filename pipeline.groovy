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
          def choiceInput = input(
            id: 'choiceInput', message: 'Merge to?',
              parameters: [[$class: 'ChoiceParameterDefinition', defaultValue: 'strDef',
              description:'describing choices', name:'nameChoice', choices: "QA\nUAT\nProduction\nDevelop\nMaster"]
          ])
          println "Value from choiceInput: ${choiceInput}"
       }
     }
   }

   stage ('BooleanParameterDefinition Test') {
     steps {
       script {
        def booleanInput = input(
          id: 'booleanInput', message: 'Was this successful?',
            parameters: [[$class: 'BooleanParameterDefinition', defaultValue: true,
              description: 'Simple Boolean ChoiceParameterDefinition', name: 'Please confirm you agree with this']
        ])
        println "Value from booleanInput: ${booleanInput}"
      }
    }
  }

  stage ('StringInput Test') {
    steps {
      script {
        def stringInput = input(
          id: 'stringInput', message: 'input your text: ', ok: 'ok',
            parameters: [string(defaultValue: 'some text',
              description: '.....', name: 'LIB_TEST')]
        )
        println "Value from stringInput: ${stringInput}"
      }
    }
  }

  stage ('PasswordParameterDefinition Test') {
    steps {
      script {
        def passwordInput = input(
          id: 'passwordInput', message: 'Was this successful?',
            parameters: [[$class: 'PasswordParameterDefinition', defaultValue: 'somerandompassword',
              description: 'Enter a Password', name: 'PasswordParameterDefinition']
        ])
        println "Value from passwordInput: ${passwordInput}"
      }
    }
  }

  stage ('FileParameterDefinition Test') {
    steps {
      script {
        def fileInput = input(
          id: 'fileInput', message: 'Select a File',
            parameters: [[$class: 'FileParameterDefinition',
              description: 'Select a File', name: 'Select a File']
        ])
        println "Value from fileInput: ${fileInput}"
      }
    }
  }

} //end Stages

}
