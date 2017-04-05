import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
    selector: 'my-logout-component',
    template: ''
})
export class MyLogOutComponent implements OnInit {

    constructor(private router: Router) {
    }

    public ngOnInit() {
        localStorage.removeItem('loggedInUserName');
        this.router.navigateByUrl('/login');
    }

}
