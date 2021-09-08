# Step you should know about deploying StatefulSets

StatefulSet creates pods sequentially one after another, each replica should have its own PV. PV can be created automatically based on StorageClass given inside StatefulSet manifest.
Once StatefulSet is created it creates PVC for each pod replica and those should be bound to its PV.

Also, you should create a special service with ClusterIP: None just to allow each Pod replicas communicate to each other though this service.
