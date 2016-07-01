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


exports.getJavaInstance = function() {
    return java;
}
