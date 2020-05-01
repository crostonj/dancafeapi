var azure = require('azure-storage');


var userTable = function(){
    var tableName = "Users"
    
    var createTable = function(){
        var tableService = azure.createTableService();

        tableService.createTableIfNotExists(tableName, function(error, result, response) {
            if (!error) {
                // result contains true if created; false if already exists
            }
        });
    };


    var insertUser = function(user)
    {

        var tableService = azure.createTableService();

        var entGen = azure.TableUtilities.entityGenerator;
        var entity = {
            PartitionKey: entGen.String('part2'),
            RowKey: entGen.String('row1'),
            boolValueTrue: entGen.Boolean(true),
            boolValueFalse: entGen.Boolean(false),
            intValue: entGen.Int32(42),
            dateValue: entGen.DateTime(new Date(Date.UTC(2011, 10, 25))),
            complexDateValue: entGen.DateTime(new Date(Date.UTC(2013, 02, 16, 01, 46, 20)))
        };

        const item = req.body;
        item["PartitionKey"] = "Partition";
        item["RowKey"] = uuid();

        // Use { echoContent: true } if you want to return the created item including the Timestamp & etag
        tableService.insertEntity(tableName, item, { echoContent: true }, function (error, result, response) {
            if (!error) {
                // This returns a 201 code + the database response inside the body
                // Calling status like this will automatically trigger a context.done()
                context.res.status(201).json(response);
            } else {
                // In case of an error we return an appropriate status code and the error returned by the DB
                context.res.status(500).json({ error: error });
            }
        });
    }


    var getUser = function(context, req){

        const tableService = azure.createTableService();

        const id = req.query.id;
        if (id) {
            // return item with RowKey 'id'
            tableService.retrieveEntity(tableName, 'Partition', id, function (error, result, response) {
                if (!error) {
                    context.res.status(200).json(response.body);
                }
                else {
                    context.res.status(500).json({error : error});
                }
            });
        }
        else {
            // return the top 100 items
            var query = new azure.TableQuery().top(100);
            tableService.queryEntities(tableName, query, null, function (error, result, response) {
                if(!error){
                    context.res.status(200).json(response.body.value);
                } else {
                    context.res.status(500).json({error : error});
                }
            });
        }
    }

    var updateUser = function (context, req) {
        context.log('Start ItemUpdate');
    
        if (req.body) {
    
            // TODO: Add some object validation logic
            const item = req.body;
    
            // Depending on how you want this to behave you can also use tableService.mergeEntity
            tableService.replaceEntity(tableName, item, function (error, result, response) {
                if (!error) {
                    context.res.status(202).json(result);
                } else {
                    context.res.status(500).json({ error: error });
                }
            });
        }
        else {
            context.res = {
                status: 400,
                body: "Please pass an item in the request body"
            };
            context.done();
        }
    };


    var deleteUser = function (context, req) {
        context.log('Start ItemDelete');
    
        const id = req.query.id;
        if (id) {
            // create a temporary object with PartitionKey & RowKey of the item which should be deleted
            var item = { PartitionKey: 'Partition', RowKey: id };
            tableService.deleteEntity(tableName, item, function (error, result, response) {
                if (!error) {
                    context.res.status(204).send();
                }
                else {
                    context.res.status(500).json({error : error});
                }
            });
        }
        else {
            // item to delete can't be found since no ID was passed
            context.res.status(404).send();
        }
    
    };

    return {
        createTable: createTable,
        insertUser: insertUser,
        getUser: getUser,
        updateUser: updateUser,
        deleteUser: deleteUser
    }
}

module.exports = userTable;

