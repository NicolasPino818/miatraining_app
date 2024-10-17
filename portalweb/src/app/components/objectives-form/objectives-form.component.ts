import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { JwtService } from '../../services/jwt/jwt.service';
import { SessionStorageService } from '../../services/storage/session-storage.service';
import { IDecodedAccessTokenInfo, IUserDetailsFormOptions } from '../../models/interfaces';
import { isPlatformBrowser, NgFor, NgIf } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../services/users/user.service';
import { Router } from '@angular/router';
import { userURLs } from '../../models/nav';

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
    age: new FormControl(null, [Validators.required, Validators.min(1), Validators.max(150), Validators.pattern('^[0-9]+$')]),
    weight: new FormControl(null, [Validators.required, Validators.min(1), Validators.pattern('^[0-9]+$')]),
    height: new FormControl(null, [Validators.required, Validators.min(1), Validators.pattern('^[0-9]+$')]),
    gender: new FormControl(null,[Validators.required]),
    diet: new FormControl(null,[Validators.required]),
    trainingType: new FormControl(null,[Validators.required]),
    trainingExperience: new FormControl(null,[Validators.required]),
    objective: new FormControl(null,[Validators.required]),
    bodyType: new FormControl(null,[Validators.required]),
    frontalPhoto: new FormControl(null,[Validators.required]),
    sidePhoto: new FormControl(null,[Validators.required]),
    backPhoto: new FormControl(null,[Validators.required]),
  });

  // Propiedades para previsualización de imágenes
  public frontalPreviewUrl: string | ArrayBuffer | null = null;
  public sidePreviewUrl: string | ArrayBuffer | null = null;
  public backPreviewUrl: string | ArrayBuffer | null = null;
  private frontalPhotoFile!: File;
  private sidePhotoFile!: File;
  private backPhotoFile!: File;
  constructor(private jwtService: JwtService,
    private userService: UserService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private router: Router,
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

  onFileSelected(event: any, photoType: string) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      
      // Previsualización del archivo
      reader.onload = () => {
        if (photoType === 'frontal') {
          this.frontalPreviewUrl = reader.result;
          this.frontalPhotoFile = file; // Almacena el archivo en una variable
        } else if (photoType === 'side') {
          this.sidePreviewUrl = reader.result;
          this.sidePhotoFile = file; // Almacena el archivo en una variable
        } else if (photoType === 'back') {
          this.backPreviewUrl = reader.result;
          this.backPhotoFile = file; // Almacena el archivo en una variable
        }
      };
  
      // Leer archivo como URL para previsualización
      reader.readAsDataURL(file);
    }
  }
  

  onSubmit() {
    if (this.form.valid) {
      const formData = new FormData();
  
      // Agregar los campos de texto al FormData
      formData.append('age', this.form.get('age')?.value);
      formData.append('weight', this.form.get('weight')?.value);
      formData.append('height', this.form.get('height')?.value);
      formData.append('gender', this.form.get('gender')?.value);
      formData.append('dietID', this.form.get('diet')?.value);
      formData.append('trainingTypeID', this.form.get('trainingType')?.value);
      formData.append('trainingExperienceID', this.form.get('trainingExperience')?.value);
      formData.append('objectiveID', this.form.get('objective')?.value);
      formData.append('bodyTypeID', this.form.get('bodyType')?.value);
  
      // Agregar los archivos seleccionados al FormData
      formData.append('frontalPhoto', this.frontalPhotoFile); // Archivo frontal
      formData.append('sidePhoto', this.sidePhotoFile); // Archivo lateral
      formData.append('backPhoto', this.backPhotoFile); // Archivo trasero
  
      // Envía el FormData al backend usando el servicio
      this.userService.saveUserDetails(this.token.sub, formData).subscribe((result) => {
        this.storage.storeToken(result.access_token);
        this.storage.storeRefreshToken(result.refresh_token);
        this.router.navigate(['/dashboard/', userURLs.clientBaseUrl]);
      });
    }
  }

}
