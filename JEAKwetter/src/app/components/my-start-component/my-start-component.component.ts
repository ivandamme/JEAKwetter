import {Component, OnInit} from '@angular/core';

import {Router} from "@angular/router";
import {MyKweetService} from "app/services/my-kweet-service/my-kweet-service.service";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";
import {MyUserModel} from "../../models/my-user-model/my-user-model";

@Component({
  selector: 'my-start-component',
  templateUrl: './my-start-component.component.html',
  styleUrls: ['./my-start-component.component.css']
})
export class MyStartComponent implements OnInit {

  public user: any;
  public searchString: string;
  public newKweetString: string;
  public showTimeline: number;
  public lastKweet: any;
  public timelineKweets: any;
  public userFollowing = new Array<MyUserModel>();
  public userFollowers = new Array<MyUserModel>();
  public foundKweets: any;


  constructor(private router: Router, private kweetService: MyKweetService, private userService: MyUserService) {
  }

  public ngOnInit() {
    this.showTimeline = 1;
    this.getUserByName(localStorage.getItem('loggedInUserName'));
    this.getFollowing();
    this.getFollowers();
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
  }

  public redirectToProfile(name: string): void {
    localStorage.setItem('clickedUsername', name);
    this.router.navigateByUrl('/otherprofile');
  }

  public removeKweet(id: number) {
    this.kweetService.delete(id).subscribe(k => {
      console.log(k);
      this.user.kweets = k;
    });
  }

  public sendKweet(): void {
    this.kweetService.create(this.user.userName, this.newKweetString).subscribe(returnedJson => {
      console.log(returnedJson);
      this.newKweetString = null;
      this.ngOnInit();
    });
  }

  public searchKweet(): void {
    this.kweetService.getByContent(this.searchString).subscribe(returnedJson => {
      console.log(returnedJson);
      this.foundKweets = returnedJson;
      this.toggleTimeline(4);
    });
  }

  public getFollowing(): void {
    this.userService.getFollowing(localStorage.getItem('loggedInUserName')).subscribe(following => {
      following.forEach(f => {
        this.userFollowing.push(f);
      });
    });
  }

  public getFollowers(): void {
    this.userService.getFollowers(localStorage.getItem('loggedInUserName')).subscribe(following => {
      following.forEach(f => {
        this.userFollowers.push(f);
      });
    });
  }


}
