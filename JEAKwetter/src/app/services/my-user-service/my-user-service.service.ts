import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from "@angular/http";
import { Observable } from "rxjs/Observable";

@Injectable()
export class MyUserService {

  private globalUrl = 'http://localhost:8080/JEAKwetter_war_exploded/';

  constructor(private http: Http) {
  }

  public getAll(): Observable<any> {
    const endPoint = 'api/users/all';
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getFollowing(name:string): Observable<any> {
    const endPoint = 'api/users/following/' + name;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getFollowers(name:string): Observable<any> {
    const endPoint = 'api/users/followers/' + name;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }


  public getById(id: number): Observable<any> {
    const endPoint = 'api/users/id' + id;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public getByName(name: string): Observable<any> {
    const endPoint = 'api/users/' + name;
    const url = this.globalUrl + endPoint;
    return this.getRequest(url);
  }

  public changePicture(userName: string, picture: string):  Observable<any> {
    const endPoint = 'api/users/changepic/';
    const url = this.globalUrl + endPoint;
    let body = 'userName=' + userName + '&picture=' + picture;
    return this.postRequest(url, body);
  }

  public changeBio(userName: string, bio: string):  Observable<any> {
    const endPoint = 'api/users/changebio/';
    const url = this.globalUrl + endPoint;
    let body = 'userName=' + userName + '&bio=' + bio;
    return this.postRequest(url, body);
  }

  public addVolger(naamLeider: string, naamVolger: string):  Observable<any> {
    const endPoint = 'api/users/addFollow/';
    const url = this.globalUrl + endPoint;
    let body = 'leader=' + naamLeider + '&following=' + naamVolger;
    return this.postRequest(url, body);
  }

  public stopVolger(naamLeider: string, naamVolger: string):  Observable<any> {
    const endPoint = 'api/users/unFollow/';
    const url = this.globalUrl + endPoint;
    let body = 'leader=' + naamLeider + '&following=' + naamVolger;
    return this.postRequest(url, body);
  }

  public inloggen(name: string, wachtwoord: string):  Observable<any> {
    const endPoint = 'api/users/login/';
    const url = this.globalUrl + endPoint;
    let body = 'userName=' + name + '&password=' + wachtwoord;
    return this.postRequest(url, body);
  }

  private getRequest(url: string): any {
    let headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    });
    return this.http.get(url.replace(/['"]+/g, ''), { headers: headers })
      .map((res: Response) => res.json())
      .catch(this.handleError);
  }

  private postRequest(url: string, body: string) {
    let headers = new Headers({
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        });
        return this.http.post(url.replace(/['"]+/g, ''), body, { headers: headers })
          .map((res: Response) => res.json())
          .catch(this.handleError);
  }

  private handleError(error: any): Observable<any> {
    console.error('An error occurred', error);
    return Observable.throw(error.message || error);
  }

}
