var javaInit = require('./javaInit');
var java = javaInit.getJavaInstance();


exports.createHashtable = function(propertiesValuesObject,cb){
    var htblColNameValue = java.newInstanceSync("java.util.Hashtable");
  for (var poperty in propertiesValuesObject) {
  if (propertiesValuesObject.hasOwnProperty(poperty)) {
    var value = propertiesValuesObject[poperty];
    htblColNameValue.putSync(poperty,value);
  }
}
  cb(htblColNameValue);
}


// java.callStaticMethod("JenaFusekiServer.SelectQueries", "getPersons", function(err, results) {
//   if(err) { console.error("ERROR ------->"+err); return; }
//     console.log("method called");
//     console.log(results);
//
// });
