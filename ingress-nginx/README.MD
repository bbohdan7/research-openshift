# Introducing Ingresses based on Nginx ingress controller

### What we need to do for this to work:
1. Install nginx ingress controller from its official website [ingress-nginx](https://kubernetes.github.io/ingress-nginx/deploy/#bare-metal)
2. It'll install everything inside its namespace called ingress-nginx and it also creates NodePort service to handle traffic from outside the cluster.
3. If you disagree with NodePort service type you can use LoadBalancer on Bare-metal but first of all you'll need something like MetalLB. So install it first on your bare-metal cluster!
4. From now you can create your own Ingress resources which will handle requests from outside the cluster and redirect it to the cluster.
5. To create Ingress resource just do the following: `$> kubectl apply -f nginx-ingress.yaml` but be sure that your nginx.yaml has been run at first and has the ClusterIP service called **nginx-app** to which your Ingress will talk to.

## What do you need to know about Ingress resource:
* Inside **metadata** it is required to specify `kubernetes.io/ingress.class: "nginx"`.
* **defaultBackend** means that if Ingress meets with unhandled request a client will be redirected to default backend instead of throwing 404 error.
* Inside **rules** we've got host **master-node.com** which can be any valid DNS (if you do not have any just modify your _hosts_ file depending on your OS).
* Path means that in order the client to be redirected to your backend service (which is **nginx-app**) the URL must be look like _http(s)://master-node.com/app_ otherwise the client will be redirected to the default backend
* Backend service is the destination ClusterIP type service exists on your cluster and which is your target from the point of view of the Ingress.

### Other notes you should know.
DNS specified inside Ingress manifest should be valid DNS name from which clients can access your application, if your do not reconcile DNS which is _http(s)://master-node.com_ in our example your ClusterIP service will never be accessible for clients. So if you specify some DNS inside your Ingress manifest please be sure that DNS is resolved by client the same as inside manifest.
