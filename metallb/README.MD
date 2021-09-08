# Bare-metal load balancer using [MetalLB](https://metallb.universe.tf) solution.

## First of all you need to do next:
1. Modify kube-proxy configmap and specify ```mode: "ipvs"``` and ```ipvs: strictARP: true```
2. Install by manifest from [MetalLB](https://metallb.universe.tf/installation/) official website
3. Apply metallb-cm.yaml which contains address pool which will be obtained by LoadBalancers to be created.

### Enjoy!
