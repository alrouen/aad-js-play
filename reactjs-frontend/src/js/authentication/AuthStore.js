import Airflux from 'airflux';
import AuthApi from './AuthApi';
import * as AuthActions from './AuthActions';

class AuthStore extends Airflux.Store {
    constructor() {
        super();
        this.listenTo( AuthActions.logout, this.onLogout );
        this.listenTo( AuthActions.enforceAuthentication, this.onEnforceAuthentication );
        this.listenTo( AuthActions.acquireToken.action.completed, this.onTokenAcquired );
    }

    onEnforceAuthentication(protectedApp, transitionToLoginScreen) {

        var authenticated = AuthApi.enforceAuthentication();
        if(authenticated) {
            return protectedApp();
        } else {
            return transitionToLoginScreen();
        }
    }

    onLogout() {
        AuthApi.logout();
    }

    onTokenAcquired(token) {
        //console.log("token", token);
    }

    get user() {
        var user = AuthApi.getUser();
        if(user) {
            return user;
        } else {
            return void 0;
        }
    }

}

module.exports = new AuthStore();