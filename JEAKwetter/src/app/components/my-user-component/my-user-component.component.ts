import { Component, OnInit } from '@angular/core';
import {MyUserModel} from "../../models/my-user-model/my-user-model";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";

@Component({
	selector: 'my-user-component',
	templateUrl: './my-user-component.component.html',
	styleUrls: ['./my-user-component.component.css']
})
export class MyUserComponent implements OnInit {

	public users = new Array<MyUserModel>();

	constructor(private myUserService: MyUserService) {
	}

	public ngOnInit() {
		this.getAll();
	}

	public getAll() {
		this.myUserService.getAll().subscribe(users => {
      users.forEach(u => {
				this.users.push(new MyUserModel(u.id,
          u.userName, u.bio, u.website, u.pictureUrl, u.location));
			});
		});
	}

}
