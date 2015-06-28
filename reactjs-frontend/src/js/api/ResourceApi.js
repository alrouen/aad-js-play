var WebApiConfiguration = require("AppConfig").WebApi;
var Uri = require("./Uri");
var authenticatedFetch = require("../authentication/authenticatedFetch");

var ResourceApi = {
    sayHello() {
        return authenticatedFetch( new Uri( "{0}/sayHello", WebApiConfiguration.publicWebHost) )
            .then( (r) => r.json())
            .catch( (error) => {
                console.log('error', error);
            });
    },
    groups() {
        return authenticatedFetch( new Uri( "{0}/groups", WebApiConfiguration.publicWebHost) )
            .then( (r) => r.json())
            .catch( (error) => {
                console.log('error', error);
            });
    },
    memberOf() {
        return authenticatedFetch( new Uri( "{0}/memberOf", WebApiConfiguration.publicWebHost) )
            .then( (r) => r.json())
            .catch( (error) => {
                console.log('error', error);
            });
    }
};

module.exports = ResourceApi;