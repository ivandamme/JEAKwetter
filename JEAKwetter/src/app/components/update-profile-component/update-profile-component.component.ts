import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { MyKweetService } from "app/services/my-kweet-service/my-kweet-service.service";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";

@Component({
    selector: 'update-profile-component',
    templateUrl: './update-profile-component.component.html',
    styleUrls: ['./update-profile-component.component.css']
})
export class UpdateProfileComponent implements OnInit {

    public user: any;

    constructor(private router: Router, private kweetService: MyKweetService, private myUserService: MyUserService) {
    }

    public ngOnInit() {
        this.getKwetteraarByName(localStorage.getItem('loggedInUserName'));
    }

    public getKwetteraarByName(name: string) : void {
        this.myUserService.getByName(name).subscribe(k => {
            console.log(k);
            this.user = k;
        });
    }

    public getKwetteraarById(id: number) : void {
        this.myUserService.getById(id).subscribe(k => {
            console.log(k);
            this.user = k;
        });
    }

    public opslaan() {
        this.myUserService.changePicture(this.user.userName, this.user.pictureUrl).subscribe(k => {
            console.log(k);
            this.user = k;
        });
        this.myUserService.changeBio(this.user.userName, this.user.bio).subscribe(k => {
            console.log(k);
            this.user = k;
            this.router.navigateByUrl('/profile');
        });
    }

}
