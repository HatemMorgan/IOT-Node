var express = require('express');
var router = express.Router();
var javaInit = require('../javascript/javaInit');
var java = javaInit.getJavaInstance();
var functions = require('../javascript/functions');


/* update person */
router.put('/person/:username', function(req, res) {
  var username = req.params.username;
functions.createHashtable(req.body,function(htblColNameValue){
  java.callStaticMethod("JenaFusekiServer.UpdateQueries", "updatePerson",username,htblColNameValue ,function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
        res.send("person with username: "+username+" updated");
  });
});
});


module.exports = router;
