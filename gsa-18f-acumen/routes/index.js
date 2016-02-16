/**
*   Router for the root of the web application.
*
*   @author Acumen Solutions, Inc.
*/
var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  //res.render('index', { title: 'Express' });
  res.senfile('./public/index.html');
});

module.exports = router;
