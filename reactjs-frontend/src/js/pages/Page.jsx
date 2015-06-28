import React from 'react';
import FluxComponent from 'airflux/lib/FluxComponent';
import AuthStore from "../authentication/AuthStore";
import * as ResourceActions from '../stores/ResourceActions';
import ResourceStore from '../stores/ResourceStore';


class SayHello extends FluxComponent {
    constructor(props) {
        super(props, { resourceStore: ResourceStore });
    }

    render(){
        var sayHelloResponse = this.state.resourceStore ? this.state.resourceStore.hello : {};
        return (
            <div>
                <h1>WebApi Sayhello response:</h1>
                <pre>{JSON.stringify(sayHelloResponse, null, '  ')}</pre>
            </div>
        );
    }
}

class Groups extends FluxComponent {
    constructor(props) {
        super(props, { resourceStore: ResourceStore });
    }

    render(){
        var groupsResponse = this.state.resourceStore ? this.state.resourceStore.groups : {};
        return (
            <div>
                <h1>WebApi groups response :</h1>
                <pre>{JSON.stringify(groupsResponse, null, '  ')}</pre>
            </div>
        );
    }
}

class MemberOf extends FluxComponent {
    constructor(props) {
        super(props, { resourceStore: ResourceStore });
    }

    render(){
        var memberOfResponse = this.state.resourceStore ? this.state.resourceStore.memberOf : {};
        return (
            <div>
                <h1>WebApi MemberOf response:</h1>
                <pre>{JSON.stringify(memberOfResponse, null, '  ')}</pre>
            </div>
        );
    }
}


class Page extends FluxComponent {
    constructor(props) {
        super(props, { resourceStore: ResourceStore});
    }

    componentDidMount() {
        ResourceActions.sayHello();
        ResourceActions.groups();
        ResourceActions.memberOf();
    }

    render() {
        var user = AuthStore.user;


        return (
            <div>
                <div className="col-sm-12">
                    <h1>Hello {user.profile.name}</h1>
                    <p>Here your user object:</p>
                    <pre>{JSON.stringify(user, null, '  ')}</pre>
                </div>
                <div className="col-sm-12">
                    <SayHello />
                </div>
                <div className="col-sm-12">
                    <Groups />
                </div>
                <div className="col-sm-12">
                    <MemberOf />
                </div>
            </div>
        );
    }
}

module.exports = Page;