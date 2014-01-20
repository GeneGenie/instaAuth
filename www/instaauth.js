
 var exec = require('cordova/exec');

Instagram = {
	auth:function(suc,fail){
	   return  exec(suc,fail, "InstagramAuth", "auth", []);
	}
}
module.exports = Instagram;


  