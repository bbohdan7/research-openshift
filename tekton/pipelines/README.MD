# Explanation about how to complete task with building image step from Dockerfile

* use command `$> kubectl apply -f resources.yaml` to create two PipelineResources for input and output
  * Input is the GitHub project which contains Dockerfile to be built
  * Output resource is the Docker image resource with private registry (You can create your own private registry from my own example at [custom private registry](https://github.com/zbogdan7/research-openshift/tree/master/private_registry)).
* Execute `$> kubectl apply -f task.yaml` to create your first task with input/output resources and the only step which is based on [buildah/buildah](https://hub.docker.com/r/buildah/buildah)
  * The step executes command `$> buildah bud --tag=my_tag:version --file=path_to_dockerfile --tls-verify=false my_context` to build an image from existing dockerfile from GitHub repository downloaded at the first step.
* The last thing you should do is to run your task by adding the task to a Pipeline or create the single TaskRun resource to verify everything works fine.
  * To do so, let's create TaskRun and specify there real path to GitHub repository and URL of private registry where to push your built image.
* That's it! =)
