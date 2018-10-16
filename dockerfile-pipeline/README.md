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
      jenkinsfilePath: "docker-daemon/docker-daemon-pipeline.groovy"
```
