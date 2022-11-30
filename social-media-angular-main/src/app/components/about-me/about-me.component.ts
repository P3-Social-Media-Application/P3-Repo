import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { aboutMe } from 'src/app/models/aboutme';
import { AngularMaterialModule } from 'src/app/modules/angular-material.module';
import { AboutInfoService } from 'src/app/services/about-info.service';

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.css']
})
export class AboutMeComponent implements OnInit {
 
 
  
  aboutMe : FormGroup;
  submitted = false;
  myInfo? : string;
 
  
  constructor(private http : HttpClient,
    private aboutInfo : AboutInfoService,
    private formBuilder : FormBuilder) { 

      this.aboutMe = this.formBuilder.group({
        aboutMe: ["",[Validators.required,]],
        
      })
    }

 

  
  

  onSubmit(){
    this.submitted = true;
    console.log(this.aboutMe.value);
    
    
    
    this.aboutInfo.submit(this.aboutMe.value).subscribe(
    data =>{ 
      
      console.log(data)
      window.location.reload();
    }
  )}
   
    
  

  ngOnInit(): void {
    this.http.get("http://localhost:8080/about/get-info", {withCredentials: true ,observe : "response"}).subscribe(
      (res : any ) => {
        console.log(res.body);
        this.myInfo = res.body.aboutMe;
        this.submitted = true;
        
      },
      err => {
        console.log(err);
      }
     )
    
  }

}
