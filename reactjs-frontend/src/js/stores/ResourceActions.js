import { Action } from 'airflux';
import WsAction from './WsAction';
import ResourceApi from "../api/ResourceApi";

export var sayHello = new WsAction( ResourceApi.sayHello );
export var groups = new WsAction( ResourceApi.groups );
export var memberOf = new WsAction( ResourceApi.memberOf );
