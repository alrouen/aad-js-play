var _           = require( 'lodash' );
var AuthApi     = require( './AuthApi' );

function authenticatedFetch( url, options ) {
    options = options || {};

    //TODO : manage token refresh case... so far related API calls are just failing...
    return AuthApi.acquireToken()
        .then((token) => {
            options.headers = {
                'Authorization': 'Bearer ' + token
            };
            return fetch( url, options );
        })
        .catch((error) => {
            console.log("acquireToken error", error);
        });
}

module.exports = authenticatedFetch;
