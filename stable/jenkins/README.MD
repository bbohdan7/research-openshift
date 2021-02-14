# Steps to deploy Jenkins with Helm

* Create a namespace for Jenkins
`$>kubectl create ns jenkins`

* Add repo to the Helm
`$>helm repo add jenkins https://charts.jenkins.io`

* Update Repo
`$>helm repo update`

* Create PersistentVolume from file jenkins-volume.yaml
`$>kubectl create -f jenkins-volume.yaml`

* Create a ServiceAccount, ClusterRole and ClusterRoleBinding by creating a file jenkins-rbac.yaml
`$>kubectl create -f jenkins-rbac.yaml`

## Steps to install Jenkins with Helm by applying the file jenkins-deploy.yaml but you should modify that file
* Enable PersistentVolume created by creating file jenkins-volume.yaml
Modify `storageClass: jenkins-pv` where jenkins-pv is your PersistentVolume's name

* Enable previously created ServiceAccount by modifying block with content:
`serviceAccount:
create: false
name: jenkins
annotations: {}`

* Change ClusterIP with NodePort to work on your local environment (Minikube)
* Modify `runAsUser: 0 fsGroup: 0` to become a root

* Finally, apply that file by running a command:
`$> helm install jenkins -n jenkins -f jenkins-deploy.yaml jenkins/jenkins`

* After your Jenkins App is ready with 2/2 pods, try to access the app from outside using your NodePort.
* Jenkins will ask you to login with username/password, you should use an admin credentials where password you can take from secrets:
`$>$pass=$(kubectl get secret jenkins -njenkins -o jsonpath="{.data.jenkins-admin-password}")`
`$>echo $(echo $pass | base64 --decode)`
* You should copy and paste the returned value into the Jenkins password field with admin username
* Enjoy!

