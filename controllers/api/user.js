//user.js (server/controllers/apis/v1/user.js)

const express = require('express');
const userService = require("../../services/v1/users/user");
const authClientRequest = require('../../middleware/authguard');
let router = express.Router();

router.get('/:userId', authClientRequest.authClientToken ,userService.getUserDetails);

module.exports = router;