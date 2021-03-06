var FetchTransformers = {
    /**
     * Checks for the status of a response. By default, fetch only reject when the
     * request completely fails to be made, not on server responses.
     */
    checkStatus(response) {
      if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response)
      } else {
        return Promise.reject(new Error(response.statusText))
      }
    },

    /**
     * Checks for the status of a response. By default, fetch only reject when the
     * request completely fails to be made, not on server responses.
     * Here on anything else then >200 and <300, we extract the json and reject the original promise
     */
    checkStatusJsonError(response) {
      if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response)
      } else {
        return new Promise( function( resolve, reject ) {
            response.json().then( ( json ) => reject( json ) );
        });
      }
    },

    parseJson( response ) {
        return response.json();
    }
};

module.exports = FetchTransformers;
