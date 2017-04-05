import { Component, OnInit } from '@angular/core';
import {MyRoleModel} from "../../models/my-role-model/my-role-model";
import {MyRoleService} from "../../services/my-role-service/my-role-service.service";

@Component({
  selector: 'my-role-component',
  templateUrl: './my-role-component.component.html',
  styleUrls: ['./my-role-component.component.css']
})
export class MyRoleComponent implements OnInit {

  public rollen = new Array<MyRoleModel>();

  constructor(private rolService: MyRoleService) {
  }

  public ngOnInit() {
    this.getAll();
  }

  public getAll() {
    this.rolService.getAll().subscribe(rollen => {
      rollen.forEach(r => {
        this.rollen.push(new MyRoleModel(r.id, r.roleName));
      });
    });
  }

}
