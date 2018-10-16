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

    stage ('Cleanup'){
      steps {
        script {
          sh "oc delete bc,dc,svc,route -l app=dockerfile-php"
        }
      }
    }

    stage ('Build App'){
      steps {
        sh "oc new-app --name=dockerfile-php --strategy=docker https://github.com/vinicius-martinez/ocp-php-dockerfile.git"
      }
    }

  }

}
