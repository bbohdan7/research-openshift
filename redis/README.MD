# Redis deployment example

In this example I will explain how to use Redis inside Kubernetes Pod.
This project contains a very simple deployment manifest with using Redis image from Docker Hub. We just need to expose a default Redis port - 6379 on which the server listening on and which client connects to.

## Basic understanding of CLI - **redis-cli**.
Inside the Pod's container run `redis-cli` commmand.

### Basically, Redis has several types for inserted data:
1. String (key=<value> where <value> is the String).
2. List (key=(<value1>, <value2>, <value3>, <value_n>)) - contains one key with more than one associated values. Order remains by pushing.
3. Set - generally, the same as List but not allowing to duplicate values and sorting order unexpected.
4. Sorted Set - the same as Set but sorted.
5. Hash - some kind of Object, very similar to JSON.

## Here I give a simple commands to manipulate key/value pairs inside Redis:
* `127.0.0.1:6378> KEYS *` - print all keys inside the database
* `127.0.0.1:6379> DBSIZE` - amount of inserted to db elements. Also you can use `127.0.0.1:6379> INFO` where on the bottom you'll see information about currently used keyspace.
* `127.0.0.1:6379> SET username "John"` - created a new key/value pair where "John" will be assigned to usersname key.
* `127.0.0.1:6379> GET username` - prints "John" as a value for username key.
* `127.0.0.1:6379> TYPE username` - prints String as username has value as a String.
* `127.0.0.1:6379> SET username:1 "Julia"` - so called Namespace

### Working with Lists

* `127.0.0.1:6379> LPUSH users "Julia"` - adds "Julia" value into users of type List, now it looks like users = ("Julia"). When you add another one `127.0.0.1:6379> LPUSH users "Victoria"` it becomes users = ("Julia", "Victoria") and so on.
* `127.0.0.1:6379> LRANGE users 0 10` - prints ranged results from the users List.
* `127.0.0.1:6379> LREM users 1 "Julia"` - removes element "Julia" from the users List. 1 - means count of occurences, if there are many "Julia" deleted will be only one.

### Working with Sets

* `127.0.0.1:6379> SADD users "Julia"` - add "Julia" element into the users SET. Note! If you'll try to add another "Julia" it will throw an error as Sets doesn't allow to duplicate values.
* `127.0.0.1:6379> SMEMBERS users` - print all elements inside users Set.
* `127.0.0.1:6379> SREM users "Julia"` - removes "Julia" value from the Set.

### Working with Hashes.
* `127.0.0.1:6379> HMSET user:1 name "John"` - creates a new Hash named user:1, add key called name and associate "John" value to this key. Another Hash can be user:2, user:3, user:4 user:n etc. Such data represents some objects just like any JSON or another data representation.
* `127.0.0.1:6379> HDEL user:1 <some_key>` - removes <some_key> from user:1 Hash

### Removing elements
* `127.0.0.1:6379> DEL <key1> <key2> <key3> <key_n>` - removes the key with all associated data.

## In the above sections I've depicted mostly used commands inside **redis-cli** CLI utility.

## There is another cool CLI utility to test performance called `**redis-benchmark**` which calculates data representing how performant your Redis server is. Just run `$> redis-benchmark`, also, type --help if you need additional parameters supplied with it.

# Enjoy!
