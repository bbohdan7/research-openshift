# Couchbase NoSQL key-value storage
This deployment contains such ports to expose:
* 8091 - Web UI. (First startup will prompt you to create a new cluster, set a new admin user, create a new server with related buckets).
* 8091-8097 port ranges is so called Unencrypted Client-To-Node ports.
* 11210 - is the main port for external connections. Usually it looks like jdbc:couchbase://<host>:11210/bucket
* 11207 - is the same as 11210 but encrypted
Couchbase structure will look like: **Cluster*->**Server*->*Bucket*->*Documents*:
1. Cluster may have multiple nodes and those node can communicate via node-to-node ports (4369, 8091-8094 etc)
2. Servers groups together many Buckets.
3. Buckets - you can treat those as a collection (in MongoDB) or just convenient Table in ordinary SQL-based DBs to store data called - Documents.
3. Documents is just like Mongo's documents, containing JSON based or other key-value pair.
### Couchbase understand SQL-like syntax called N1QL which is easily to learn if you already know SQL.
All opeartions can be performed via Web UI listening on port 8091. There is a special workbench available to perform N1QL queries.
### There is a CLI utility called CBQ, which can be accessed from the running pods where you can run your queries:
  * `cbq>SELECT * FROM bucket;`, `SELECT meta().id, name, email FROM bucket WHERE TONUMBER(meta().id) >= 10` etc.
  * You should understand that id isn't stored inside your Bucket's document directly, instead it exists as metadata, and this one can be accessed via **meta().id** and has a type of VARCHAR, to convert it to the NUMERIC type just take it into TONUMBER() as this: `cbq> SELECT * FROM bucket WHERE **TONUMBER(meta().id) = 10**`

### There are problems to fetch all first time because it prints out an error that there is no any primary indexes, to resolve this just run this query inside cbq CLI utility:
`cbq>CREATE PRIMARY INDEX ON default:bucket;`

## Enjoy!
