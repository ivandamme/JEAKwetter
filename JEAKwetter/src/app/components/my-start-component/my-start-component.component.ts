import { Component, OnInit } from '@angular/core';

import { Router } from "@angular/router";
import { MyKweetService } from "app/services/my-kweet-service/my-kweet-service.service";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";

@Component({
    selector: 'my-start-component',
    templateUrl: './my-start-component.component.html',
    styleUrls: ['./my-start-component.component.css']
})
export class MyStartComponent implements OnInit {

    public user: any;
    public searchString: string;
    public showTimeline: number;
    public lastKweet: any;
    public timelineKweets: any;


    constructor(private router: Router, private kweetService: MyKweetService, private userService: MyUserService) {
    }

    public ngOnInit() {
        this.showTimeline = 1;
        this.getUserByName(localStorage.getItem('loggedInUserName'));
    }



    public getTimelineKweets(): void {
        this.kweetService.getTimeLine(this.user.userName).subscribe(returnedKweets => {
            console.log(returnedKweets);
            this.timelineKweets = returnedKweets;
        });
    }

    public getUserByName(name: string): void {
        this.userService.getByName(name).subscribe(k => {
            console.log(k);
            this.user = k;
            if (this.user.kweets && this.user.kweets[this.user.kweets.length - 1])
                this.lastKweet = this.user.kweets[0];
            this.getTimelineKweets();
        });
    }


    public toggleTimeline(value: number): void {
        this.showTimeline = value;
        if (value == 3) {
            //this.hashtagService.getAllByName()
        }
    }

  public removeKweet(id: number) {
    this.kweetService.delete(id).subscribe(k => {
      console.log(k);
      this.user.kweets = k;
    });
  }

    public sendKweet(): void {

    }

    public searchKweet(): void {

    }

}
