import { Action } from 'airflux';
import { OnReadyActions }   from './OnReady';

/**
 * A thin wrapper for the web services actions, updating the global loading status.
 * We could maybe include here the automatic JSON request transformers call.
 */
export default class WsAction extends Action {
    constructor( apiCall ) {
        super();
        this.asyncResult( function() {
            OnReadyActions.updateStatus( false );
            return apiCall.apply( this, arguments ).then( ( p ) => {
                OnReadyActions.updateStatus( true );
                return p;
            });
        });

        return this.asFunction;
    }
}