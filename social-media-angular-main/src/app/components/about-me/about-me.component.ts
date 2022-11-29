import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AboutInfoService } from 'src/app/services/about-info.service';

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.css']
})
export class AboutMeComponent implements OnInit {

  constructor(private http : HttpClient,
    private aboutInfo : AboutInfoService) { }

  firstName?: string;
  lastName?: string;
  email?: string;

  
  

  submitInfo(){
  this.aboutInfo.submit
    
  }

  ngOnInit(): void {

    this.http.get("http://localhost:8080/auth/user", {withCredentials: true, observe : "response"}).subscribe(
      (res : any ) => {
        console.log(res);
        this.firstName = res.body.firstName;
        this.lastName = res.body.lastName;
        this.email = res.body.email;
        
        
      },
      err => {
        console.log(err);
      }
     )
  }

}

