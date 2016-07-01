var express = require('express');
var router = express.Router();

var javaInit = require('../javascript/javaInit');
var java = javaInit.getJavaInstance();

/* delete a person . */
router.delete('/person/:userName', function(req, res) {
    var username = req.params.userName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deletePerson",username ,function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
      console.log("person with username: "+username+" is deleted");
      res.send("person with username: "+username+" is deleted");
  });
});

/* delete an application . */
router.delete('/application/:applicationName', function(req, res) {
    var applicationName = req.params.applicationName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteApplication",applicationName ,function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
      console.log("application with applicationName: "+applicationName+" is deleted");
      res.send("application with applicationName: "+applicationName+" is deleted");
  });
});

/* delete personUsesApplicationRelation . */
router.delete('/PersonUsesApplicationRelation/:username/:applicationName', function(req, res) {
    var applicationName = req.params.applicationName;
    var username = req.params.username;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deletePersonUsesApplicationRelation",username,applicationName ,function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
      console.log("A person with username: "+username+" uses application with applicationName: "+applicationName+" relation is deleted");
      res.send("A person with username: "+username+" uses application with applicationName: "+applicationName+" relation is deleted");
  });
});


/* delete a system . */
router.delete('/system/:systemName', function(req, res) {
    var systemName = req.params.systemName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteSystem",systemName ,function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
      console.log("System with name: "+systemName+" is deleted");
      res.send("System with name: "+systemName+" is deleted");
  });
});


/* delete a ApplicationUsesSystemRelation . */
router.delete('/ApplicationUsesSystemRelation/:applicationName/:systemName', function(req, res) {
    var applicationName = req.params.applicationName;
    var systemName = req.params.systemName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteApplicationUsesSystemRelation",applicationName,systemName,function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
    console.log("An application with applicationName: "+applicationName+" uses system with systemName: "+systemName+" relation is deleted");
    res.send("An application with applicationName: "+applicationName+" uses system with systemName: "+systemName+" relation is deleted");
  });
});


/* delete a SystemHasSubSystemRelation . */
router.delete('/SystemHasSubSystemRelation/:systemName/:subSystemName', function(req, res) {
    var systemName = req.params.systemName;
    var subSystemName = req.params.subSystemName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteSystemHasSubSystemRelation",systemName,subSystemName ,function(err, results) {
    if(err) { console.error("ERROR ------->"+err); return; }
      console.log("A system with systemName: "+systemName+" has subSystem with systemName: "+subSystemName+" relation is deleted");
      res.send("A system with systemName: "+systemName+" has subSystem with systemName: "+subSystemName+" relation is deleted");
  });
});
module.exports = router;
