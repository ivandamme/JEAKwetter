import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { Http, Response, RequestOptions, Headers } from "@angular/http";
import {MyLocationModel} from "../../models/my-location-model/my-location-model";


@Injectable()
export class MyLocationService {

  private globalUrl = 'http://localhost:8080/JEAKwetter_war_exploded/';

  constructor(private http: Http) {
  }

  public getAll(): Observable<MyLocationModel[]> {
    const endPoint = 'rest/locatie/get/more';
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getById(id: number): Observable<MyLocationModel> {
    const endPoint = 'rest/locatie/get/one/id/' + id;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getByName(name: string): Observable<MyLocationModel> {
    const endPoint = 'rest/locatie/get/one/name/' + name;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public create(name: string):  Observable<MyLocationModel> {
    const endPoint = 'rest/locatie/post/insert/';
    const url = this.globalUrl + endPoint;
    let body = 'name=' + name;
    return this.postRequest(url, body);
  }

  public update(id: number, name: string):  Observable<MyLocationModel> {
    const endPoint = 'rest/locatie/post/update/';
    const url = this.globalUrl + endPoint;
    let body = 'id=' + id + '&name=' + name;
    return this.postRequest(url, body);
  }

  public delete(id: number):  Observable<MyLocationModel[]> {
    const endPoint = 'rest/locatie/post/delete/';
    const url = this.globalUrl + endPoint;
    let body = 'id=' + id;
    return this.postRequest(url, body);
  }

  private getRequest(url: string): any {
    let headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    });
    return this.http.get(url, { headers: headers })
      .map((res: Response) => res.json())
      .catch(this.handleError);
  }

  private postRequest(url: string, body: string) {
    let headers = new Headers({
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        });
        return this.http.post(url, body, { headers: headers })
          .map((res: Response) => res.json())
          .catch(this.handleError);
  }

  private handleError(error: any): Observable<any> {
    console.error('An error occurred', error);
    return Observable.throw(error.message || error);
  }

}
