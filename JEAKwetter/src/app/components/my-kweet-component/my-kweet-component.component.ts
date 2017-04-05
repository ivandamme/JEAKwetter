import { Component, OnInit } from '@angular/core';
import { MyKweetModel } from "app/models/my-kweet-model/my-kweet-model";
import { MyKweetService } from "app/services/my-kweet-service/my-kweet-service.service";

@Component({
  selector: 'my-kweet-component',
  templateUrl: './my-kweet-component.component.html',
  styleUrls: ['./my-kweet-component.component.css']
})
export class MyKweetComponent implements OnInit {

  public kweets = new Array<MyKweetModel>();

  constructor(private kweetService: MyKweetService) {
  }

  public ngOnInit() {
    this.getAll();
  }

  public getAll() {
    this.kweetService.getAll().subscribe(kweets => {
      kweets.forEach(k => {
        this.kweets.push(k)
        //this.kweets.push(new MyKweetModel(k.id, k.text, k.date, k.eigenaar));
      });
    });
  }

}
