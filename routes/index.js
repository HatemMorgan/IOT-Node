var express = require('express');
var router = express.Router();
var javaInit = require('../javascript/javaInit');
var java = javaInit.getJavaInstance();
/* GET home page. */
router.get('/getPersons', function(req, res, next) {
  java.callStaticMethod("JenaFusekiServer.SelectQueries", "getPersons", function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
      console.log("method called");
      console.log(results);
        res.send(results);
  });

});

module.exports = router;
