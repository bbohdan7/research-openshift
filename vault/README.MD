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

# Basic usage of HashiCorp Vault with CLI/API

## Secret engines list
`vault secrets list` - this command prints all of the engines enabled

## Generating new Auth token
`vault token create` - it'll be printed out to the bash, once dissappeared you'll never know it again so be careful.

## Getting/Revoking/Renewing Auth tokens based on their accessors.
`vault list auth/token/accessors` - this command will give you list of accessors which tokens have been generated for. Once you've got those you'll be able to Revoke/Renew them.
`vault token revoke <accessor>` - revoking token from the accessor
`vault token renew <accessor>` - renewing token for the accessor

## Creating new KV (key/value) secret using CLI.
`vault kv put secret/mysecret 1=first 2=second 3=third 4=forth 5=fifth` - creating KV secret with 5 elements from 1 to 5.
## Accessing newly created KV secret using CLI
`vault kv list secret` - printing out all of the KV in secrets engine.
`vault kv get secret/mysecret` - printing information about mysecret KV.
## Accesing the aforementioned KV using RESTful API.
`curl --header "X-Vault-Token: <token>" --request LIST http://vault:8200/v1/secret/metadata` - this will give you JSON with information about all KV storages.
`curl --header "X-Vault-Token: <token>" --request GET http://vault:8200/v1/secret/data/mysecret` - printing JSON with information about key/value paires and another additional information about KV.

# Working with Database secret engine.
## To enable Database engine just run this command: `vault secrets enable database` or use Web UI.
## Let's create MySQL database secret engine using CLI.
`vault write database/config/mysql-db plugin_name=mysql-database-plugin connection_url="{{username}}:{{password}}@tcp(<your_mysql_host:3306>)/" allowed_roles="myrole" username="root" password="root_password"`
## To list all created Database storages just run this:
`vault list database/config` - this will give you the list of existing Database connections.
### When you successfully connected to MySQL DB using connection you're able to rotate root password to forget already known password for all users who knew it. After that, only Vault can now the new one.
## To fetch new credentials you need to create a role from which you will get your new credentials to access database.
`vault write database/roles/myrole db_name=mysql-db creation_statement="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}'; GRANT ALL PRIVILEGES ON *.* TO 'name'@'%';" default_ttl="1h" max_ttl="24h"`
This command will create a new role which will create new temporary user with password which Vault will know and could give everybody who has access to it. As you can see abovementioned command has creation statement which is a MySQL script that creates a new user with its own password, then granting all privileges to it. This user will existing from 1h to 24h then be deleted from MySQL database and who's been logged in earlier won't have acceess anymore and will need to generate a new credentials to relogin to database again.
## To generate new credentials just run this command:
`vault read database/creds/myrole` - this will print you an information about new user and password. Just copy those credentials and use as username/password to authenticate yourself in the database.

## Working with databases using RESTful API
## List of database storages
`curl -H "X-Vault-Token: <token>" -X LIST http://vault:8200/v1/database/config` - resuling JSON will contain information about existing database secret connections.
`curl -H "X-Vault-Token: <token>" -X GET http://vault:8200/v1/database/config/mysql-db` - this will show you an information about database connection (without credentials)

## Retrieving information about existing roles.
`curl -H "X-Vault-Token: <token>" -X LIST http://vault:8200/v1/database/roles` - JSON gives you data about existing role names inside database engine.

## Generating credentials using API
`curl -H "X-Vault-Token: <token>" -X GET http://vault:8200/v1/database/creds/myrole` - inside JSON you'll find temporary credentials to access database i.e. username/password.




## Enjoy!
