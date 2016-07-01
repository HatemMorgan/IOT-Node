// var java = require("java");
// java.classpath.push("commons-lang3-3.1.jar");
// java.classpath.push("commons-io.jar");
//
// var list1 = java.newInstanceSync("java.util.ArrayList");
// console.log(list1.sizeSync()); // 0
// list1.addSync('item1');
// console.log(list1.sizeSync()); // 1
//
// java.newInstance("java.util.ArrayList", function(err, list2) {
//   list2.addSync("item1");
//   list2.addSync("item2");
//   console.log(list2.toStringSync()); // [item1, item2]
// });
//
// var ArrayList = java.import('java.util.ArrayList');
// var list3 = new ArrayList();
// list3.addSync('item1');
// list3.equalsSync(list1); // true
//
// console.log(list3.toStringSync());
//
// var charArray = java.newArray("char", "hello world\n".split(''));
// console.log("----->"+charArray[0]);
// var byteArray = java.newArray(
//   "byte",
//   "hello world\n"
//     .split('')
//     .map(function(c) { return java.newByte(String.prototype.charCodeAt(c)); }));
// console.log(byteArray[]);
console.log("before requiring");
var javaInit = require('./javaInit');
console.log("after requiring");
var java = javaInit.getJavaInstance();

// console.log(java.classpath);
//
// var list1 = java.newInstanceSync("./target/");

  // java.callStaticMethod("Server.MainController", "test", function(err, results) {
  //   if(err) { console.error(err); return; }
  //     console.log("method called");
  //     console.log(results);
  // });

// java.callStaticMethod("Server.MainController", "insertData", function(err, results) {
//   if(err) { console.error("ERROR ------->"+err); return; }
//     console.log("method called");
// });


java.callStaticMethod("JenaFusekiServer.SelectQueries", "getPersons", function(err, results) {
  if(err) { console.error("ERROR ------->"+err); return; }
    console.log("method called");
    console.log(results);

});
