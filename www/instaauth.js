
 var exec = require('cordova/exec');

Instagram = {
	auth:function(){
	   return  exec(function(a){alert(a);}, function(a){alert(a);}, "InstagramAuth", "auth", []);
	}
}
module.exports = Instagram;


  