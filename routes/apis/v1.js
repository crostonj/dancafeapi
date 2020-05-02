

const userController = require("../../controllers/api/user");
const authController = require("../../controllers/api/auth");

const express = require('express');
let router = express.Router();

router.use('/users', userController);

router.use('/auth', authController);

module.exports = router;