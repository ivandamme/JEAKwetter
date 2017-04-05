import { Component, OnInit } from '@angular/core';
import {MyLocationModel} from "../../models/my-location-model/my-location-model";
import {MyLocationService} from "../../services/my-location-service/my-location-service.service";

@Component({
  selector: 'my-location-component',
  templateUrl: './my-location-component.component.html',
  styleUrls: ['./my-location-component.component.css']
})
export class MyLocationComponent implements OnInit {

  public locations = new Array<MyLocationModel>();

  constructor(private locatieService: MyLocationService) {
  }

  public ngOnInit() {
    this.getAll();
  }

  public getAll() {
    this.locatieService.getAll().subscribe(locaties => {
      locaties.forEach(l => {
        this.locations.push(new MyLocationModel(l.id,l.longitude, l.latitude,l.city));
      });
    });
  }

}
