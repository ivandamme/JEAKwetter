import {Component, OnInit} from '@angular/core';

import {MyUserModel} from "app/models/my-user-model/my-user-model";
import {Router} from "@angular/router";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";

@Component({
  selector: 'my-login-component',
  templateUrl: './my-login-component.component.html',
  styleUrls: ['./my-login-component.component.css']
})
export class MyLogInComponent implements OnInit {

  public user: MyUserModel;
  public name: string;
  public password: string;

  constructor(private router: Router, private userService: MyUserService) {
  }

  ngOnInit() {
  }

  public formProcess() {
    this.inloggen(this.name, this.password);
  }

  public inloggen(name: string, pass: string) {
    this.userService.inloggen(name, pass).subscribe(k => {
      if (k != null) {
        this.user = new MyUserModel(k.id, k.userName, k.bio, k.website, k.pictureUrl, k.location);
        sessionStorage.setItem('loggedInUserName', this.user.userName);
        this.router.navigateByUrl('/profile');
      }
      else {
        this.router.navigateByUrl('/kweet');
      }
    });
  }

}
