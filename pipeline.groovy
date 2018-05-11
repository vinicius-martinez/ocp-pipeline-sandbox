pipeline {
  stages{
    stage ('Cluster Test'){
      openshift.withCluster() {
        echo "Hello from ${openshift.cluster()}'s default project: ${openshift.project()}"
      }
    }
  }
}
