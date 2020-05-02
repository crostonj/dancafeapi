
const apiRoute = require("./apis/v1");

var init = function(server) {
    server.get('*', function (req, res, next) {
        console.log('Request was made to: ' + req.originalUrl);
        return next();
    });
    
    server.use('/api', apiRoute);
};


module.exports = {
    init: init
};