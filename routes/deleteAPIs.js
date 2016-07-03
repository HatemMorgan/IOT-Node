var express = require('express');
var router = express.Router();

var javaInit = require('../javascript/javaInit');
var java = javaInit.getJavaInstance();

/* delete a person . */
router.delete('/person/:userName', function(req, res) {
    var username = req.params.userName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deletePerson",username ,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
      console.log("person with username: "+username+" is deleted");
      res.send("person with username: "+username+" is deleted");
  });
});

/* delete an application . */
router.delete('/application/:applicationName', function(req, res) {
    var applicationName = req.params.applicationName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteApplication",applicationName ,function(err, results) {
  if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
      console.log("application with applicationName: "+applicationName+" is deleted");
      res.send("application with applicationName: "+applicationName+" is deleted");
  });
});

/* delete personUsesApplicationRelation . */
router.delete('/personUsesApplicationRelation/:username/:applicationName', function(req, res) {
    var applicationName = req.params.applicationName;
    var username = req.params.username;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deletePersonUsesApplicationRelation",username,applicationName ,function(err, results) {
if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
      console.log("A person with username: "+username+" uses application with applicationName: "+applicationName+" relation is deleted");
      res.send("A person with username: "+username+" uses application with applicationName: "+applicationName+" relation is deleted");
  });
});


/* delete a system . */
router.delete('/system/:systemName', function(req, res) {
    var systemName = req.params.systemName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteSystem",systemName ,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
      console.log("System with name: "+systemName+" is deleted");
      res.send("System with name: "+systemName+" is deleted");
  });
});


/* delete a ApplicationUsesSystemRelation . */
router.delete('/applicationUsesSystemRelation/:applicationName/:systemName', function(req, res) {
    var applicationName = req.params.applicationName;
    var systemName = req.params.systemName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteApplicationUsesSystemRelation",applicationName,systemName,function(err, results) {
  if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
    console.log("An application with applicationName: "+applicationName+" uses system with systemName: "+systemName+" relation is deleted");
    res.send("An application with applicationName: "+applicationName+" uses system with systemName: "+systemName+" relation is deleted");
  });
});


/* delete a SystemHasSubSystemRelation . */
router.delete('/systemHasSubSystemRelation/:systemName/:subSystemName', function(req, res) {
    var systemName = req.params.systemName;
    var subSystemName = req.params.subSystemName;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteSystemHasSubSystemRelation",systemName,subSystemName ,function(err, results) {
if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
      console.log("A system with systemName: "+systemName+" has subSystem with systemName: "+subSystemName+" relation is deleted");
      res.send("A system with systemName: "+systemName+" has subSystem with systemName: "+subSystemName+" relation is deleted");
  });
});

/* delete a device . */
router.delete('/device/:macaddress', function(req, res) {
    var macaddress = req.params.macaddress;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteDevice",macaddress ,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
      console.log("A Device with macaddress: "+macaddress+ " is deleted");
      res.send("A Device with macaddress: "+macaddress  + " is deleted");
  });
});

/* delete a senesingDevice . */
router.delete('/senesingDevice/:UUID', function(req, res) {
    var UUID = req.params.UUID;
  java.callStaticMethod("JenaFusekiServer.DeleteQueries", "deleteSensingDevice",UUID ,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
      console.log("A senesingDevice with UUID: "+UUID+ " is deleted");
      res.send("A senesingDevice with UUID: "+UUID+ " is deleted");
  });
});

module.exports = router;
