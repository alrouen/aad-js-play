var React           = require( 'react' );
var Router          = require( 'react-router' );
var FluxComponent   = require( 'airflux/lib/FluxComponent' );
var classnames      = require( 'classnames' );

var AuthActions     = require( './authentication/AuthActions' );
var AuthStore       = require( './authentication/AuthStore' );
var OnReadyStore    = require( './stores/OnReady' ).OnReadyStore;
var OnReadyActions  = require( './stores/OnReady' ).OnReadyActions;

function injectRouter( cl ) {
    cl.contextTypes = {
        router: React.PropTypes.func.isRequired
    };

    return cl;
}

/**
 * Small loader
 */
var HomeLoader = <div id="loading-home" className='container'>
                    <div id="loader">
                        <span className="lead"><i className="fa fa-fw fa-spinner fa-spin"></i>&nbsp;Loading stuff, getting things ready...</span>
                    </div>
                 </div>;


class NavBar extends FluxComponent {
    constructor( props ) {
        super( props, { isReady: OnReadyStore } );
    }

    logout(e) {
        e.preventDefault();
        AuthActions.logout();
    }

    render() {

        var spinnerClasses = classnames({"fa fa-lg fa-spinner fa-spin": !this.state.isReady});

        return (
            <nav id='nav' className="navbar navbar-fixed-top" role="navigation">
                <ul className="navbar-right">
                    <li>
                        <a href="https://account.activedirectory.windowsazure.com/profile/" target="_blank" title="Profile" className="navbar-link">
                            <i className='fa fa-user'/>&nbsp;
                        </a>
                    </li>
                    <li>
                        <a href="#" onClick={this.logout} title="Logout" className="navbar-link">
                            <i className='fa fa-sign-out'/>&nbsp;{AuthStore.user.profile.name}
                        </a>
                    </li>
                </ul>
            </nav>
        );
    }
}
injectRouter( NavBar );

/**
 *
 * HOME PAGE
 *
 */
class Home extends React.Component {
    constructor( props ) {
        super( props )
    }

    componentDidMount(){
        OnReadyActions.updateStatus( true )
    }

    render(){
        return (
            <div ref="app" id="wrapper" >
                <NavBar/>
                <div id="side"></div>
                <div id="content" className="container">
                    <Router.RouteHandler />
                </div>
            </div>
        );
    }
}

module.exports = injectRouter( Home );
