# Cassandra is a powerful NoSQL database which organized as ordinary SQL-based DB.
## It uses KEYSPACES as a databases. Inside KEYSPACES there are tables just like everywhere on relational ones.
This deployment is very simple, it is based on Docker Hub's Cassandra image additionally adding one configuration to cassandra.yaml which make available using indexes which I'll explain a little bit later.

### Using Cassandra with CLI:
* run `cqlsh -u username -p password` cqlsh - stands for Cassandra Query Language. It is a language very similar to native SQL instead of some cases. It doesn't support BETWEEN statement and ORDER BY (IMHO, I didn't resolved how to use those clauses).
* To list all the keyspaces run: `cqlsh> DESCRIBE KEYSPACES;` - it'll print out the list of existing keyspaces.
* To create a new KEYSPACE: `cqlsh>CREATE KEYSPACE IF NOT EXISTS mykeyspace WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : '1'}`;
* If you want to use table and so one without specifying KEYSPACE name: `cqlsh> USE mykeyspace;`
* Creating table: `cqlsh> CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY, name VARCHAR, email: VARCHAR, created_at TIMESTAMP);`
* Insertion: `cqlsh> INSERT INTO users(id, name, email, created_at) VALUES(1, 'Test', 'test@email', TOTIMESTAMP(NOW()))`;

### You may encounter on several errors while doing selections, here are some of them:
* `cqlsh> SELECT id, name FROM users WHERE id = 5` - OK, won't cause an error.
* `cqlsh> SELECT id, name FROM users WHERE id >= 5` - No, it'll cause error because Cassandra doesn't allow to use filtering using relational operators explicitly, to allow this do this: `cqlsh> SELECT id, name FROM users WHERE id >= 5 **ALLOW FILTERING**`
* `cqlsh> SELECT id, name, email FROM users WHERE name LIKE '%John%'` - Throws an error because there is no any indexes which will allow doing searching using mode - CONTAINS. In order to allow this you should add a custom index: `cqlsh> CREATE CUSTOM INDEX IF NOT EXISTS ix_users_name ON users (name) USING 'org.apache.cassandra.index.sasi.SASIIndex' WITH OPTIONS = { 'mode' : 'CONTAINS', 'analyzed': 'true', 'analyzer_class' : 'org.apache.cassandra.index.sasi.analyzer.StandardAnalyzer' }`;

### To delete everything from the table do this: `cqlsh> TRUNCATE users;`
There are cases when we need to print information about our Cassandra database, in order to do this type this command from outside **cqlsh** CLI utility but using tool called *nodetool*, e.g. `$> nodetool status` and you'll see an information about your server with data center information which can be used inside applications like SpringBoot.

## Enjoy!
