import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";
import {MyUserModel} from "../../models/my-user-model/my-user-model";

@Component({
  selector: 'other-profile-component',
  templateUrl: './other-profile-component.component.html',
  styleUrls: ['./other-profile-component.component.css']
})
export class OtherProfileComponent implements OnInit {

  public user: MyUserModel;
  public showLeiders = true;
  public alreadyFollowing = false;

  public loggedInUser: any;
  public loggedUserFollowing = new Array<MyUserModel>();

  public UserFollowing = new Array<MyUserModel>();
  public UserFollowers = new Array<MyUserModel>();


  constructor(private router: Router, private myUserService: MyUserService) {
  }

  public ngOnInit() {
    let username = localStorage.getItem('clickedUsername');
    let loggedInUserName = localStorage.getItem('loggedInUserName');

    this.getUserByName(username);
    this.getLoggedInUser(loggedInUserName);

    this.getLoggedUserFollowing();
    this.getUserFollowing();

  }


  public getUserByName(name: string): void {
    this.myUserService.getByName(name).subscribe(k => {
      this.myUserService.getFollowers(localStorage.getItem('clickedUsername')).subscribe(followers => {
        this.UserFollowers = followers;
        this.user = k;
        if (this.user && this.UserFollowers) {
          let loggedInUserName = localStorage.getItem('loggedInUserName');
          for (let i = 0; i < this.UserFollowers.length; i++) {
            if (this.UserFollowers[i].userName == loggedInUserName)
              this.alreadyFollowing = true;
          }
        }
      });
    });
  }

  public getLoggedInUser(name: string): void {
    this.myUserService.getByName(name).subscribe(k => {
      this.loggedInUser = k;
    });
  }

  public showLeidersOrVolgers(status: boolean) {
    this.showLeiders = status;
  }

  public redirectToProfile(name: string): void {
    localStorage.setItem('clickedUsername', name);
    if (name == localStorage.getItem('loggedInUserName'))
      this.router.navigateByUrl('/profile');
    window.location.reload();
  }

  public followUser() {
    this.myUserService.addVolger(localStorage.getItem('loggedInUserName'), localStorage.getItem('clickedUsername')).subscribe(leader => {
        this.alreadyFollowing = true;
        this.myUserService.getFollowers(localStorage.getItem('clickedUsername')).subscribe(followers => {
          this.UserFollowers = followers;
        });
      });
  }

  public unfollowUser() {
    this.myUserService.stopVolger(localStorage.getItem('loggedInUserName'), localStorage.getItem('clickedUsername')).subscribe(leader => {
      this.alreadyFollowing = false;
      this.myUserService.getFollowers(localStorage.getItem('clickedUsername')).subscribe(followers => {
        this.UserFollowers = followers;
      });
    });
  }

  public getLoggedUserFollowing(): void {
    this.myUserService.getFollowing(localStorage.getItem('loggedInUserName')).subscribe(following => {
      this.loggedUserFollowing = (following);
    });
  }

  public getUserFollowing(): void {
    this.myUserService.getFollowing(localStorage.getItem('clickedUsername')).subscribe(following => {
      this.UserFollowing = following;
    });
  }
}
