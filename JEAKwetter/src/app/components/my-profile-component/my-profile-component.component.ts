import {Component, OnInit} from '@angular/core';
import {MyKweetService} from "app/services/my-kweet-service/my-kweet-service.service";
import {Router} from "@angular/router";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";
import {MyUserModel} from "../../models/my-user-model/my-user-model";

@Component({
  selector: 'my-profile-component',
  templateUrl: './my-profile-component.component.html',
  styleUrls: ['./my-profile-component.component.css']
})
export class MyProfileComponent implements OnInit {

  public user: any;
  public userFollowing = new Array<MyUserModel>();
  public userFollowers = new Array<MyUserModel>();
  public showLeiders = true;

  constructor(private router: Router, private kweetService: MyKweetService, private myUserService: MyUserService) {
  }

  public ngOnInit() {
    this.getUserByName(localStorage.getItem('loggedInUserName'));
    this.getFollowing();
    this.getFollowers();

  }

  public getUserByName(name: string): void {
    this.myUserService.getByName(name).subscribe(k => {
      console.log(k);
      this.user = k;
    });
  }

  public removeKweet(id: number) {
    this.kweetService.delete(id).subscribe(k => {
      console.log(k);
      this.user.kweets = k;
    });
  }

  public showLeidersOrVolgers(status: boolean) {
    this.showLeiders = status;
  }

  public redirectToEditProfile(): void {
    this.router.navigateByUrl('/updateprofile');
  }

  public redirectToProfile(name: string): void {
    localStorage.setItem('clickedUsername', name);
    this.router.navigateByUrl('/otherprofile');
  }

  public getFollowing(): void {
    this.myUserService.getFollowing("Niek").subscribe(following => {
      following.forEach(f => {
        this.userFollowing.push(f);
      });
    });
  }

  public getFollowers(): void {
    this.myUserService.getFollowers("Niek").subscribe(following => {
      following.forEach(f => {
        this.userFollowers.push(f);
      });
    });
  }


}
