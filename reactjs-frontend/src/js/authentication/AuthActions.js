import Airflux from 'airflux';
import AuthApi from './AuthApi';

export var logout = new Airflux.Action().asSyncFunction;
export var enforceAuthentication = new Airflux.Action().asSyncFunction;
export var acquireToken = new Airflux.Action().asyncResult(AuthApi.acquireToken.bind(AuthApi)).asFunction;
