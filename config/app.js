// app.js file
const express = require('express');
const bodyParser = require('body-parser');
//const mongoose = require('mongoose');
const expressValidator = require('express-validator')

module.exports = function () {
    let server = express(),
        create,
        start;

    create = (config, db) => {
        let routes = require("../routes");
        // set all the server things
        server.set('env', config.env);
        server.set('port', config.port);
        server.set('hostname', config.hostname);
        
        // add middleware to parse the json
        app.use(logger('dev'));
        app.use(express.json());
        app.use(express.urlencoded({ extended: false }));
        app.use(cookieParser());
        app.use(express.static(path.join(__dirname, 'public')));
                server.use(bodyParser.json());
        server.use(expressValidator())

        //connect the database
        //mongoose.connect(
        //    db.database,
        //    { 
        //        useNewUrlParser: true,
        //        useCreateIndex: true
        //    }
        //);

        // Set up routes

        server.use(function(req, res, next) {
            next(createError(404));
          });
          
          // error handler
          server.use(function(err, req, res, next) {
            // set locals, only providing error in development
            res.locals.message = err.message;
            res.locals.error = req.server.get('env') === 'development' ? err : {};
          
            // render the error page
            res.status(err.status || 500);
            res.render('error');
          });

          
        routes.init(server);
    };

    
    start = () => {
        let hostname = server.get('hostname'),
            port = server.get('port');
        server.listen(port, function () {
            console.log('Express server listening on - http://' + hostname + ':' + port);
        });
    };
    return {
        create: create,
        start: start
    };
};