/**
 * Created by Niek on 1-5-2017.
 */
import { Component } from '@angular/core';

@Component({
  selector: 'my-websocket-component',
  templateUrl: './my-websocket-component.component.html'
})
export class MyWebsocketComponent {

  public username: string
  public websocket: WebSocket;
  public messageString: string;
  public messages: string;

  constructor() {

    this.username = localStorage.getItem('loggedInUserName');
    console.log(this.username);
    this.websocket = new WebSocket('ws://localhost:64550/Kwetter_war_exploded/socket/' + this.username);

    this.websocket.onerror = function (event) {
      onError(event);
    };

    this.websocket.onopen = function (event) {
      onOpen(event);
    };

    this.websocket.onmessage = function (event) {
      onMessage(event);
    };

    function onMessage(event) {
      console.log(event.data);
      document.getElementById('messages').innerHTML += '<br />Received message: ' + event.data;
      //this.messages += '<br />Received message: ' + event.data;
    };

    function onOpen(event) {
      document.getElementById('messages').innerHTML = 'Connection established';
      //this.messages = 'Connection established';
    };

    function onError(event) {
      alert(event.data);
    };
  }

  public send() {
    this.websocket.send(this.messageString);
    this.messageString = null;
  }

}
