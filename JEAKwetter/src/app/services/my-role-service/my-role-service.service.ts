import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from "@angular/http";
import { Observable } from "rxjs/Observable";

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {MyRoleModel} from "../../models/my-role-model/my-role-model";

@Injectable()
export class MyRoleService {

  private globalUrl = 'http://localhost:8080/JEAKwetter_war_exploded/';

  constructor(private http: Http) {
  }

  public getAll(): Observable<MyRoleModel[]> {
    const endPoint = 'rest/rol/get/more';
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getById(id: number):  Observable<MyRoleModel> {
    const endPoint = 'rest/rol/get/one/id/' + id;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getByName(name: string):  Observable<MyRoleModel> {
    const endPoint = 'rest/rol/get/one/name/' + name;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public create(name: string):  Observable<MyRoleModel> {
    const endPoint = 'rest/rol/post/insert/';
    const url = this.globalUrl + endPoint;
    let body = 'name=' + name;
    return this.postRequest(url, body);
  }

  public update(rol: MyRoleModel):  Observable<MyRoleModel> {
    const endPoint = 'rest/rol/post/update/';
    const url = this.globalUrl + endPoint;
    let body = 'id=' + rol.id + '&name=' + rol.roleName;
    return this.postRequest(url, body);
  }

  public delete(id: number):  Observable<MyRoleModel[]> {
    const endPoint = 'rest/rol/post/insert/';
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
