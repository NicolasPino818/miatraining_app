import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { JwtService } from '../../services/jwt/jwt.service';
import { SessionStorageService } from '../../services/storage/session-storage.service';
import { IDecodedAccessTokenInfo, IUserDetailsFormOptions } from '../../models/interfaces';
import { isPlatformBrowser, NgFor, NgIf } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../services/users/user.service';

@Component({
  selector: 'app-objectives-form',
  standalone: true,
  imports: [NgIf, NgFor, ReactiveFormsModule],
  templateUrl: './objectives-form.component.html',
  styleUrl: './objectives-form.component.css'
})
export class ObjectivesFormComponent implements OnInit{

  public token!: IDecodedAccessTokenInfo;
  public userDetailsFormOptions!: IUserDetailsFormOptions;
  public form: FormGroup = new FormGroup({
    age: new FormControl(null,[Validators.required, Validators.min(1), Validators.max(150), Validators.pattern('[0-9]')]),
    weight: new FormControl(null,[Validators.required, Validators.min(1), Validators.pattern('[0-9]')]),
    height: new FormControl(null,[Validators.required, Validators.min(1), Validators.pattern('[0-9]')]),
    gender: new FormControl(null,[Validators.required]),
    diet: new FormControl(null,[Validators.required]),
    trainingType: new FormControl(null,[Validators.required]),
    trainingExperience: new FormControl(null,[Validators.required]),
    objective: new FormControl(null,[Validators.required]),
    bodyType: new FormControl(null,[Validators.required]),
    frontalPhoto: new FormControl(null,[Validators.required]),
    sidePhoto: new FormControl(null,[Validators.required]),
    backPhoto: new FormControl(null,[Validators.required]),
  })

  constructor(private jwtService: JwtService,
    private userService: UserService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private storage: SessionStorageService){
      if(isPlatformBrowser(this.platformId)) {
        this.token = this.jwtService.getDecodedAccessToken(this.storage.getToken() as string);
      }
  }

  ngOnInit(): void {
    this.getUserDetailsFormOptions();
  }

  getUserDetailsFormOptions(){
    this.userService.getUserDetailsFormOptions().subscribe((options)=>{
      this.userDetailsFormOptions = options;
    });
  }

  onKeyDowninputNumberNoDecimal(event:any){
    const regex = /^[0-9]$/;
    const allowedKeys = ['Backspace', 'ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight', 'Delete', 'Tab'];
    if(!regex.test(event.key) && !allowedKeys.includes(event.key)) 
      event.preventDefault();
  }

  onSubmit(){
    console.log(this.form)
  }

}
