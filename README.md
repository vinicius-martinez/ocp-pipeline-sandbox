# Openshift Jenkins Pipeline Showcase

The following project was created to showcase some Jenkins features provided by [Openshift](https://www.openshift.com/)

**Contributions and suggestions are much appreciated!**

## Deploy on OpenShift

In order to deploy this pipeline, please execute the following steps:

1. Logging in your current Openshift instance, [Minishift](https://github.com/minishift/minishift), [Openshift all-in-one](https://github.com/openshift/origin/blob/master/docs/cluster_up_down.md) or [CDK](https://developers.redhat.com/products/cdk/overview/)
2. Create a Project (namespace if you're familiar with Kubernetes)
3. After creating your project, select "Add to Project > Import YAML / JSON" and import the following snippet:

```
kind: "BuildConfig"
apiVersion: "v1"
metadata:
  name: "sample-pipeline"
spec:
  source:
    type: "Git"
    git:
      uri: "https://github.com/vinicius-martinez/ocp-pipeline-sandbox"
  strategy:
    type: "JenkinsPipeline"
    jenkinsPipelineStrategy:
      jenkinsfilePath: "pipeline.groovy"
```

## Oficial References

For additional details about Openshift & Jenkins integration, please refer to:

[Openshift Pipeline](https://docs.openshift.org/latest/dev_guide/openshift_pipeline.html)

[Openshift Pipeline Builds](https://docs.openshift.org/latest/dev_guide/dev_tutorials/openshift_pipeline.html)

[Configuring Pipeline Execution](https://docs.openshift.org/latest/install_config/configuring_pipeline_execution.html)

[Continuous Integration and Deployment (CI/CD)](https://docs.openshift.org/latest/dev_guide/migrating_applications/continuous_integration_and_deployment.html)

[Openshift CI/CD Demo](https://github.com/OpenShiftDemos/openshift-cd-demo)

[Jenkins Client Plugin](https://github.com/openshift/jenkins-client-plugin)
