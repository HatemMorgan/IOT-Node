var express = require('express');
var router = express.Router();
var javaInit = require('../javascript/javaInit');
var java = javaInit.getJavaInstance();
var functions = require('../javascript/functions');


/* update person */
router.put('/person/:username', function(req, res) {
  var username = req.params.username;
  console.log("username is --->"+username);
  console.log(req.body);
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
router.put('/miniServerLocation/:miniServerName', function(req, res) {
  var miniServerName = req.params.miniServerName;
functions.createHashtable(req.body,function(htblColNameValue){
  java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateMiniServerLocation",miniServerName,htblColNameValue ,function(err, results) {
if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
        res.send("MiniServer with miniServerName: "+miniServerName+" location is updated");
  });
});
});


/* update communicating Device connected to a senesingDevice  */
router.put('/CommunicatingDeviceOfSensingDevice/:senesingDeviceUUID/:communicatingDeviceMacaddress', function(req, res) {

  var senesingDeviceUUID = req.params.senesingDeviceUUID;
  var communicatingDeviceMacaddress = req.params.communicatingDeviceMacaddress;


  java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateCommunicatingDeviceOfSensingDevice",senesingDeviceUUID ,communicatingDeviceMacaddress,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
        res.send("A senesingDevice with UUID: "+senesingDeviceUUID+" connected to a new communicatingDevice with macaddress: "+communicatingDeviceMacaddress);

});
});


/* update communicating Device   */
router.put('/CommunicatingDevice/:communicatingDeviceMacaddress', function(req, res) {

  var communicatingDeviceMacaddress = req.params.communicatingDeviceMacaddress;

  functions.createHashtable(req.body,function(htblColNameValue){
    java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateCommunicatingDevice",communicatingDeviceMacaddress,htblColNameValue ,function(err, results) {
      if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
          res.send("Communicating Device with MacAdress: "+communicatingDeviceMacaddress+"  is updated");
    });
  });
  });


  /* update Service which describes an IOT services provided by a device */
  router.put('/service/:serviceName', function(req, res) {

    var serviceName = req.params.serviceName;

    functions.createHashtable(req.body,function(htblColNameValue){
      java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateService",serviceName,htblColNameValue ,function(err, results) {
        if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
            res.send("A service with serviceName: "+serviceName+"  is updated");
      });
    });
    });

    /* update miniServer location */
    router.put('/objectLocation/:objectName', function(req, res) {
      var objectName = req.params.objectName;
    functions.createHashtable(req.body,function(htblColNameValue){
      java.callStaticMethod("JenaFusekiServer.UpdateObjectsQueries", "updateObjectLocation",objectName,htblColNameValue ,function(err, results) {
    if(err) { res.send("<h1>"+err+"</h1>" ); console.log("ERROR ------->"+err); return; }
            res.send("Object with objectName: "+objectName+" location is updated");
      });
    });
    });

module.exports = router;
