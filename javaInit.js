"use strict";
var fs = require("fs");
var java = require("java");
var mvn = require('node-java-maven');



var baseDir = "./target/dependency";
var dependencies = fs.readdirSync(baseDir);

dependencies.forEach(function(dependency){
    console.log('adding ' + dependency + ' to classpath');
    java.classpath.push(baseDir + "/" + dependency);
})

java.classpath.push("./target/classes");
java.classpath.push("./target/test-classes");
//java.classpath.push('commons.io.jar');
// java.classpath.push('./src');
// java.classpath.push('./target/dependency');
//
// mvn(function(err, mvnResults) {
//   if (err) {
//     return console.error('could not resolve maven dependencies', err);
//   }
//   mvnResults.classpath.forEach(function(c) {
//     console.log("heeee");
//   console.log('adding ' + c + ' to classpath');
//     java.classpath.push(c);
//   });

//  var Version = java.import('org.apache.lucene.util.Version');
  //java.import('org.apache.jena.update.UpdateFactory');
//});

exports.getJavaInstance = function() {
    return java;
}
