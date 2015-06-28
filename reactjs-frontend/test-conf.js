const WebApiHost = "localhost:9000";

var AppConfig = {
    WebApi: {
        host            : WebApiHost,
        publicWebHost   : `http://${WebApiHost}`
    },

    AAD: {
        tenant:'aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee',
        clientId:'ffffffff-gggg-hhhh-iiii-jjjjjjjjjjjj'
    }
};

module.exports = AppConfig;
