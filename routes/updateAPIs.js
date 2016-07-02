var express = require('express');
var router = express.Router();
var javaInit = require('../javascript/javaInit');
var java = javaInit.getJavaInstance();
var functions = require('../javascript/functions');


/* update person */
router.put('/person/:username', function(req, res) {
  var username = req.params.username;
functions.createHashtable(req.body,function(htblColNameValue){
  java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updatePerson",username,htblColNameValue ,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
        res.send("person with username: "+username+" is updated");
  });
});
});


/* update application */
router.put('/application/:applicationName', function(req, res) {
  var applicationName = req.params.applicationName;
functions.createHashtable(req.body,function(htblColNameValue){
  java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateApplication",applicationName,htblColNameValue ,function(err, results) {
  if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
        res.send("application with applicationName: "+applicationName+" is updated");
  });
});
});

/* update miniServer location */
router.put('/miniServer/:miniServerName', function(req, res) {
  var miniServerName = req.params.miniServerName;
functions.createHashtable(req.body,function(htblColNameValue){
  java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateMiniServerLocation",miniServerName,htblColNameValue ,function(err, results) {
if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
        res.send("MiniServer with miniServerName: "+miniServerName+" location is updated");
  });
});
});


/* update communicating Device connected to a senesingDevice  */
router.put('/updateCommunicatingDeviceOfSensingDevice/:senesingDeviceUUID/:communicatingDeviceMacaddress', function(req, res) {

  var senesingDeviceUUID = req.params.senesingDeviceUUID;
  var communicatingDeviceMacaddress = req.params.communicatingDeviceMacaddress;


  java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateCommunicatingDeviceOfSensingDevice",senesingDeviceUUID ,communicatingDeviceMacaddress,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
        res.send("A senesingDevice with UUID: "+senesingDeviceUUID+" connected to a new communicatingDevice with macaddress: "+communicatingDeviceMacaddress);

});
});


/* update communicating Device connected to a senesingDevice  */
router.put('/updateCommunicatingDevice/:communicatingDeviceMacaddress', function(req, res) {

  var communicatingDeviceMacaddress = req.params.communicatingDeviceMacaddress;

  functions.createHashtable(req.body,function(htblColNameValue){
    java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateCommunicatingDevice",communicatingDeviceMacaddress,htblColNameValue ,function(err, results) {
      if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
          res.send("Communicating Device with MacAdress: "+communicatingDeviceMacaddress+"  is updated");
    });
  });
  });


module.exports = router;
