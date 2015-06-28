var Airflux = require("airflux");

var OnReadyActions = {
    updateStatus: new Airflux.Action().asFunction
};

class OnReadyStore extends Airflux.Store {
    constructor() {
        super();
        this.isReady = false;
        this.listenTo( OnReadyActions.updateStatus, this.statusUpdated );
    }

    value() {
        return this.isReady;
    }

    statusUpdated(isReady) {
        this.isReady = isReady;

        if( isReady ) {
            setTimeout( () => this.trigger( this.value() ), 500 );
        }
        else {
            this.trigger(this.isReady);
        }
    }
}


module.exports = {
    OnReadyActions: OnReadyActions,
    OnReadyStore  : new OnReadyStore()
};
