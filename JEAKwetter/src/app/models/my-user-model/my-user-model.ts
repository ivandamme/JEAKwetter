import {MyLocationModel} from "../my-location-model/my-location-model";

export class MyUserModel {
  constructor(public id: number,
              public userName: string,
              public bio: string,
              public website: string,
              public pictureUrl: string,
              public location: MyLocationModel) {
  }
}
