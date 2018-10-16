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

    stage ('Build App'){
      steps {
        git url: 'https://github.com/vinicius-martinez/ocp-php-dockerfile.git'
        sh "oc new-build --name=ocp-php-dockerfile --strategy=docker -l app=ocp-php-dockerfile  || echo 'Build exists'"
        sh "oc start-build ocp-php-dockerfile"
        sh "oc new-app ocp-php-dockerfile"
      }
    }

  }

}
