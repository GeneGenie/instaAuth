cordova.define("com.plugin.instagramAuth.InstagramAuth", function(require, exports, module) {
 var exec = require('cordova/exec');

Instagram = {
	auth:function(suc,fail){
	   return  exec(suc,fail, "InstagramAuth", "auth", []);
	},
    open:function(id,suc,fail){
        return  exec(suc,fail, "InstagramAuth", "open", [id]);
    }
}
module.exports = Instagram;


  });
