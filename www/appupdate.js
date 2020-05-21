var exec = require('cordova/exec');

module.exports = {
    update: function (action,arg) {
        exec(function(message){//成功回调function
            console.log(message);
        },
        function(message){//失败回调function
            alert('检查更新失败');
        }, 'Appupdate', action, [arg]);
    }
};
