pipeline {
  stages{
    stage ('Cluster Test'){
      script {
        openshift.withCluster() {
          echo "Hello from ${openshift.cluster()}'s default project: ${openshift.project()}"
        }
      }
    }
  }
}
