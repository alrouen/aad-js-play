// https://github.com/github/fetch
// require here because it's polyfill...
require( 'whatwg-fetch' );
var AuthActions = require('./authentication/AuthActions');
var React   = require('react');
var Router  = require('react-router');
var Home    = require('./home');
var Page = require( "./pages/Page" );

var protectedApp = () => {

    /**
     * The routes of your application
     */
    const routes = (
        <Router.Route path="/" handler={Home}>
            <Router.DefaultRoute handler={Page} />
            <Router.Route name="page" path="/page" addHandlerKey={true} handler={Page}/>
            <Router.NotFoundRoute handler={Page}/>
        </Router.Route>
    );

    /**
     * Render your React application into the DOM.
     * The correct way is to always render into a div and not directly the body, as third party scripts
     * can add scripts inside the body.
     */
    var HomeApp = Router.run( routes, function (Handler) {
        React.render(<Handler/>, document.getElementById('app-container'));
    });

};

var transitionToLoginScreen = () => {
    React.render(<div>No valid auth. Redirecting to login form...</div>, document.getElementById('app-container'));
};

AuthActions.enforceAuthentication(protectedApp, transitionToLoginScreen);
