import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiEndpoints } from '../../models/apiEndpoints';
import { BehaviorSubject, Observable } from 'rxjs';
import { IProfileInfo } from '../../models/interfaces';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  userProfile$: BehaviorSubject<IProfileInfo|null> = new BehaviorSubject<IProfileInfo|null>(null);

  constructor(private http: HttpClient) { }

  getProfileInfo(email:string): Observable<IProfileInfo>{
    email = email.toLowerCase();
    return this.http.get<IProfileInfo>(apiEndpoints.user+'/profile/'+email);
  }
}
