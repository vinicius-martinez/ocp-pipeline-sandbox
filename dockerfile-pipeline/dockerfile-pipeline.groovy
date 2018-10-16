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
          echo 'Cleaning Application resources'
          sh "oc delete all -l app=dockerfile-php"
        }
      }
    }

    stage ('SCM Checkout'){
      steps {
        script {
          echo 'Checking out git repository'
          git url: 'https://github.com/vinicius-martinez/ocp-php-dockerfile.git'
        }
      }
    }

    stage ('Build App'){
      steps {
        sh "oc new-app . -l app=dockerfile-php --name=dockerfile-php --strategy=docker"
      }
    }

    stage ('Expose App'){
      steps {
        echo sh(returnStdout: true, script: 'oc get svc dockerfile-php')
        sh "oc expose svc dockerfile-php"
      }
    }

  }

}
