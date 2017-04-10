import {Component, OnInit} from '@angular/core';
import {MyKweetService} from "../../services/my-kweet-service/my-kweet-service.service";
import {MyKweetModel} from "../../models/my-kweet-model/my-kweet-model";

@Component({
  selector: 'homecomponent',
  templateUrl: './homecomponent.html',
  styleUrls: ['./homecomponent.html']
})
export class MyHomeComponent implements OnInit {
  public kweets = new Array<MyKweetModel>();

  constructor(private kweetService: MyKweetService) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  public getAll() {
    this.kweetService.getAll().subscribe(kweets => {
      kweets.forEach(k => {
        this.kweets.push(k)
        console.log(this.kweets[0].text)
      });
    });
  }
}
