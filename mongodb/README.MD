# mongoDB deployment example.
By default mongo uses port 27017 which you should provide inside deployment manifest. Also, access to the server provided without authentication just using url with database name.
### To provide initial database you should use CLI utility within container called - `$> mongo` (if there are credentials you'll need to use this sample `$> mongo -u <username> -p <passoword>`)
After you've successfully executed **mongo** CLI utility you can do such commands:

* `> show dbs` - view all created databases which you can switched on.
* `> use dbname` - switching to dbname (if it doesn't exists mongo will creates it automatically).
* `> db` - just printing out the current database base.
* `> show collections` - viewing all the collections inside the current db.

###Working with collections (listing, inserting, removing documents into/from the collections).

* `> db.createCollection("users")` - creating an empty collection called "users" inside the database we're using.
* `> db.users.insert({id: 1, name: "John", email: "john@email"})` - creating new JSON object as a Document inside users collection.
* `> db.users.find()` - listing all the documents inside the collection `> db.users.find().**pretty()**` for pretty printed.
* `> db.users.remove({})` - remove all documents from the collection
* `> db.users.remove({id: 1})` - remove element with __id: 1__ from the collection.

### Filtering documents
In order to filter documents you're able to specify concrete values associated with keys or use relational operators.
`> db.users.find({id: 1})` - this will print JSON object with id: 1 if exists.
`> db.users.find{{id: {$gt: 10}}}` - all objects with id > 10
`> db.users.find({id: {$gte: 10}})` - the same as previous but id >= 10
`> db.users.find({id: {$eq: 5}})` - the same as just using {id: 5}
`> db.users.find({id: {$ne: 5}, name: /^Jo/})` - print all except id:5 and filter results which names starts with Jo (using regular expression, should find something like: John, Jonathan, Joseph etc).

Basically, you can you these special relational operators: $eq, $ne, $lt, $gt, $lte, $gte. Also, there are two more special operations you can use:
* `> db.users.find({name: {$in: ["Albert", "John", "Robert"]}})` - this compares name with array of names and returns result if there's at least one inside it.
* `> db.users.find({name: {$nin: ["Julia", "Monica", "Lily"]}})` - this one the same as $in but otherwise - not in.

### Retrieving specific values.

* `> db.users.find({}, {_id: 0, name: 1, email: 0})` - first parameter is the filter, here we'd like to get all. The second is the field selector, in our case _id won't printed, name will be and email won't be. As we guessed 0 means we don't need this to be printed and 1 - we want to print.
### Sorting

* `> db.users.find().sort({id: -1})` - listing all with sorted by id in descending order.
* `> db.users.find().sort({id: 1})` - sorting by id in ascending order.

### Calculate count
* `> db.users.count()` - prints number representing amount of documents inside the users collection.
* `> db.users.find({id: {$gt: 50}}).count()` - firstly, we trying to find all documents with id > 50 and then print the count of results.

### Remove and drop collections
* `> db.users.remove({})` - removing all documents from the collection.
* `> db.users.drop()` - removes the collection itself.

### Drop database
* `> db.users.dropDatabase()` - removes the database with all collections and documents.

## Here I gave a basic commands on every case.
# Enjoy!
