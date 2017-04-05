import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";

@Component({
    selector: 'other-profile-component',
    templateUrl: './other-profile-component.component.html',
    styleUrls: ['./other-profile-component.component.css']
})
export class OtherProfileComponent implements OnInit {

    public user: any;
    public showLeiders = true;
    public alreadyFollowing = false;
    public loggedInUser: any;

    constructor(private router: Router, private myUserService: MyUserService) {
    }

    public ngOnInit() {
        let username = localStorage.getItem('clickedUsername');
        let loggedInUserName = localStorage.getItem('loggedInUserName');
        this.getUserByName(username);
        this.getLoggedInUser(loggedInUserName);
    }

    public getUserByName(name: string): void {
        this.myUserService.getByName(name).subscribe(k => {
            console.log(k);
            this.user = k;
            if (this.user && this.user.volgers) {
                let loggedInUserName = localStorage.getItem('loggedInUserName');
                for (let i = 0; i < this.user.volgers.length; i++) {
                    console.log(this.user.volgers[i].profielNaam);
                    console.log(loggedInUserName);
                    if (this.user.volgers[i].profielNaam == loggedInUserName)
                        this.alreadyFollowing = true;
                }
            }
        });
    }

    public getKwetteraarById(id: number): void {
        this.myUserService.getById(id).subscribe(k => {
            console.log(k);
            this.user = k;
        });
    }

    public getLoggedInUser(name: string): void {
        this.myUserService.getByName(name).subscribe(k => {
            console.log(k);
            this.loggedInUser = k;
        });
    }

    public showLeidersOrVolgers(status: boolean) {
        this.showLeiders = status;
    }

    public startVolgen(naamLeider: string, naamVolger: string): void {
        this.myUserService.addVolger(naamLeider, naamVolger).subscribe();
    }

    public stopVolgen(naamLeider: string, naamVolger: string): void {
        this.myUserService.stopVolger(naamLeider, naamVolger).subscribe();
    }

    public redirectToProfile(name: string): void {
        localStorage.setItem('clickedUsername', name);
        if (name == localStorage.getItem('loggedInUserName'))
            this.router.navigateByUrl('/profile');
        window.location.reload();
    }

    public followUser() {
        this.startVolgen(this.loggedInUser.profielNaam, this.user.profielNaam);
        this.ngOnInit();
        window.location.reload();
    }

    public unfollowUser() {
        this.stopVolgen(this.loggedInUser.profielNaam, this.user.profielNaam);
        this.ngOnInit();
        window.location.reload();
    }

}
