  var exec = require('cordova/exec');

    Instagram = {
        auth: function (suc, fail) {
            return  exec(suc, fail, "InstagramAuth", "auth", []);
        },
        open: function (id, suc, fail) {
            if (id)
                return  exec(suc, fail, "InstagramAuth", "open", [id]);
            else
                return  exec(suc, fail, "InstagramAuth", "openApp", []);

        }
    }
    module.exports = Instagram;