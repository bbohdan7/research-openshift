# How to deploy Tekton Triggers
1. Run `kubectl apply -f trigger.yaml`
  it'll create three API-resources: EventListener, TriggerBinding and TriggerTemplate
2. Also, it'll create a special *event-listener* called **el-payment-processor-listener** with service for it.
  You can run `kubectl get all` to see that service listening on port 8080 but there is a trick that the port 8080 are bound to 8000 and if you'll need to
  create a NodePort service you should create it with port=8000 targetPort=8000 and nodePort=what_ever_you_want.
3. Go to the GitHub's repository to the Settings, select Webhooks menu and create one:
  * You should create NodePort (or LoadBalancer if possible) and forward it to nodeport=8000
  * Inside your Router settings you should forward local IP address with NodePort to your external IP and any IP address to expose your NodePort service to the World. e.g. github-listener-service:31234 -> external-ip:7000
  * Enter your external-ip:7000 to the webhook payload url and choose another settings you'd like.
4. Try to commit and push some changes to your project and if everything's set up correctly you'll see that your **pod/el-payment-processor-listener** will receive message from webhook.
5. Enjoy!
