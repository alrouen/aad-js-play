var AADConfiguration = require("AppConfig").AAD;

//Global Config Values & Instantiate ADAL AuthenticationContext
var config = {
    instance: 'https://login.microsoftonline.com/',
    tenant: AADConfiguration.tenant,
    clientId: AADConfiguration.clientId,
    postLogoutRedirectUri: window.location.origin,
    cacheLocation: 'sessionStorage'
};

class AuthApi {
    constructor(){
        this.authContext = new AuthenticationContext(config);
    }

    logout() {
        this.authContext.logOut();
    }

    loginFlow() {
        this.authContext.config.redirectUri = window.location.href;
        this.authContext.login();
    }

    enforceAuthentication() {
        // Check For & Handle Redirect From AAD After Login
        var isCallback = this.authContext.isCallback(window.location.hash);
        this.authContext.handleWindowCallback();
        this.lastLoginError = this.authContext.getLoginError();
        if (isCallback && !this.lastLoginError) {
            window.location = this.authContext._getItem(this.authContext.CONSTANTS.STORAGE.LOGIN_REQUEST);
        }

        // -----
        //TODO : manage login error, throw app exception ?
        // -----

        var user = this.authContext.getCachedUser();
        if(user) {
            return true;
        }
        this.loginFlow();
        return false;
    }

    getUser() {
        return this.authContext.getCachedUser();
    }

    acquireToken(clientId) {
        return new Promise((resolve,reject) => {
            this.authContext.acquireToken(clientId || config.clientId, (error, token) => {
                if(error || !token) {
                    return reject(error);
                }
                return resolve(token);
            });
        });
    }

}

export default new AuthApi();
