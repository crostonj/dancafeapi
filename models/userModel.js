var azure = require('azure-storage');

module.exports = function(){
    var tableName = "Users"

    var init = function(){
        var tableService = azure.createTableService();

        tableService.createTableIfNotExists(tableName, function(error, result, response) {
            if (!error) {
                // result contains true if created; false if already exists
            }
        });
    };

    var FindOne = function(req){

        const tableService = azure.createTableService();

        const user = req.body
        if (user) {
            // return item with RowKey 'id'
            var query = new azure.TableQuery().where('email == ?', user.email);
            tableService.retrieveEntity(tableName, query, null, function (error, result, response) {
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
            var query = new azure.TableQuery().where('email == ?', user.email);
            tableService.queryEntities(tableName, query, null, function (error, result, response) {
                if(!error){
                    context.res.status(200).json(response.body.value);
                } else {
                    context.res.status(500).json({error : error});
                }
            });
        }
    };

    var FindByKey = function(req){
        const tableService = azure.createTableService();

        const key = req;

        // return item with RowKey 'id'
        var query = new azure.TableQuery().where('email == ?', key.email);
        tableService.retrieveEntity(tableName, query, null, function (error, result, response) {
            if (!error) {
                context.res.status(200).json(response.body);
            }
            else {
                context.res.status(500).json({error : error});
            }
        });

    }
    var Insert = function(user)
    {

        var tableService = azure.createTableService();

        user["PartitionKey"] = "Partition";
        user["RowKey"] = uuid();

        init();

        // Use { echoContent: true } if you want to return the created item including the Timestamp & etag
        tableService.insertEntity(tableName, user, { echoContent: true }, function (error, result, response) {
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
    
    return {
        FindOne: FindOne,
        Insert: Insert,
        FindByKey: FindByKey
    }
}