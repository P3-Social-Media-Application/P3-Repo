import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { aboutMe } from '../models/aboutme';

@Injectable({
  providedIn: 'root'
})
export class AboutInfoService {

  _url= 'http://localhost:8080/about/about-info'
  constructor(private _http: HttpClient) { }
  
  submit(info : aboutMe) {

    return  this._http.post<any>(this._url, info, {withCredentials: true , observe: "response" as "body"} )
     
  }
}
