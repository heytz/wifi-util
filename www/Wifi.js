var exec = require('cordova/exec');

exports.getCurrentSSID = function (success, error) {
    exec(success, error, "Wifi", "getCurrentSSID", []);
};
