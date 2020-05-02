//users.js (server/services/v1/users/users.js)

var userModel = require('../../../models/userModel');

const getUserDetails = async (req,res,next) => {

    let { info } = req.body;

//    let user = await userModel.findById(userId).select('name , email');
    var user = await userModel.FindByKey({"email" : info.email});
    
    if(!user){
        return res.status(404).json({
            "errors":[{
                "msg" : " no user found"
            }]
        });
    }

    return res.status(200).json({
        "success" : [{
            "msg" : " user fetched successfully",
            "data" : user
        }]
    });
}

var create = function(user){
    
    storage.insertUser(user);
}

module.exports = {
        getUserDetails: getUserDetails,
        create : create
    };

