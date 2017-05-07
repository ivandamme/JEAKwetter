import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";

@Component({
  selector: 'my-logout-component',
  template: ''
})
export class MyLogOutComponent implements OnInit {

  constructor(private router: Router, private userService: MyUserService) {
  }

  public ngOnInit() {
    this.uitloggen(sessionStorage.getItem('loggedInUserName'));
  }

  public uitloggen(name: string) {
    this.userService.uitloggen(name).subscribe(k => {
      sessionStorage.removeItem('loggedInUserName');
      this.router.navigateByUrl('/login');
    });
  }


}
