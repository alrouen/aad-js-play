import Airflux from 'airflux';
import * as ResourceActions from './ResourceActions';

class ResourceStore extends Airflux.Store {
    constructor() {
        super();
        this.hello = null;
        this.groups = null;
        this.memberOf = null;

        this.listenTo( ResourceActions.sayHello.completed , this.helloRecv );
        this.listenTo( ResourceActions.groups.completed , this.groupsRecv );
        this.listenTo( ResourceActions.memberOf.completed, this.memberOfRecv );
    }

    get state() {
        return {
            hello    : this.hello,
            groups   : this.groups,
            memberOf : this.memberOf
        };
    }

    helloRecv(hello) {
        this.hello = hello;
        this.publishState();
    }

    groupsRecv(groups) {
        this.groups = groups;
        console.log(groups);
        this.publishState();
    }

    memberOfRecv(memberOf) {
        this.memberOf = memberOf;
        this.publishState();
    }

}

export default new ResourceStore();
