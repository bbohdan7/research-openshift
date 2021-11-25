# Example of Hashicorp Vault secret storage
Here I'm giving an example of using Vault within Kubernetes environment.
### Steps to deploy this app:
1. Run `kubectl apply -f vault.yaml`. I'd like to mention that in order to run Vault containers one should specify **cap-add=IPC_LOCK**, inside **vault.yamv** file you can see alternative to that param
using **securityContext:capabilities:add:["IPC_LOCK"]** parameter.
2. After the application has been successfully deployed, created services to access it if necessary
3. When you'll try to access Vault Web UI you will encounter with auth form where you should select 'Token' and paste Root Token (this token you're able to see by seeing logs `kubectl logs pod/vault`).
4. On Web UI you can perfom all necessary operations related to Vault.
5. Also, it is recommended to use CLI on which you can manage all of the secrets. To try out CLI you just need to access your pod by entering this command: `kubectl exec -it pod/vault -- sh` and then you'll be redirected inside container's shell just like you use SSH connection, then you're able to use `vault secrets list`, `vault auth`, `vault kv list secret/` and other vault's commands.

### I'd like to recommend using Vault application as Stateful one using PersistentVolumes and StatefulSets.

## Enjoy!
