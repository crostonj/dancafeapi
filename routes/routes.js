var express = require('express');
var indexRouter = require('./routes/apis/index');
var usersRouter = require('./routes/apis/users');


var routes = function(){
    var init = function(server){
        app.use('/', indexRouter);
        app.use('/users', usersRouter);
        
        // catch 404 and forward to error handler
        app.use(function(req, res, next) {
          next(createError(404));
        });
        
    };

    return {
        init: init
    };
}