import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { Http, Response, RequestOptions, Headers } from "@angular/http";
import { MyKweetModel } from "app/models/my-kweet-model/my-kweet-model";

@Injectable()
export class MyKweetService {

  private globalUrl = 'http://localhost:8080/JEAKwetter_war_exploded/';

  constructor(private http: Http) {
  }

  public getAll(): Observable<MyKweetModel[]> {
    const endPoint = 'api/kweets/all';
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getById(id: number): Observable<MyKweetModel> {
    const endPoint = 'api/kweets/' + id;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }


  public getByUserId(id: number): Observable<MyKweetModel[]> {
    const endPoint = 'api/kweets/user/' + id;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getByContent(content: string): Observable<MyKweetModel[]> {
    const endPoint = 'api/kweets/content/' + content;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }


  public create(naam: string, content: string):  Observable<MyKweetModel> {
    const endPoint = 'api/kweets/insert/';
    const url = this.globalUrl + endPoint;
    let body = 'userName=' + naam + '&text=' + content;
    return this.postRequest(url, body);
  }

  public delete(id: number):  Observable<MyKweetModel[]> {
    const endPoint = 'api/kweets/delete/';
    const url = this.globalUrl + endPoint;
    let body = 'id=' + id;
    return this.postRequest(url, body);
  }

  public getTimeLine(userName: string): Observable<any> {
    const endPoint = 'api/kweets/timeline/' + userName;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
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
