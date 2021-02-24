# This Repository contains deployment descriptors which were successfully tested on both Kubernetes and OpenShift

Approximate description of content:

* **alpine** - is a test image to build with infinite loop created in order to test command `~$ kubectl exec -it pod/alpine-pod -- sh` which helps newbies to see what happens inside the working container
* **jenkins** - based on **Helm** application for **CI/CD** conception working on **Jenkins**
* **private_registry** - is a private **[Docker](https://github.com/docker) Registry** with self-signed certificates
* **stable** - is the main application consists of three microservices:
  * **MySQL** database service depending on **ConfigMap** and **Secret**
  * **Backend** microservice based on [**WebSphere Liberty**](https://www.ibm.com/cloud/websphere-liberty) application server
  * **Frontend** microservice based on [**Node.js**](https://github.com/nodejs) runtime environment with [**React.js**](https://github.com/facebook/react) library
* Please refer to the stable [_README.MD_](https://github.com/zbogdan7/research-openshift/blob/master/stable/README.MD) file to see how to deploy all of them and how those work together in more detail
