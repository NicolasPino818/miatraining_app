import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  private TOKEN_KEY = 'JWT_TOKEN';
  private REFRESH_TOKEN_KEY = 'JWT_REFRESH_TOKEN';

  constructor() { }

  getToken(): string | null{
    return sessionStorage.getItem(this.TOKEN_KEY);
  }
  storeToken(token:string){
    sessionStorage.setItem(this.TOKEN_KEY, token);
  }
  removeToken(){
    sessionStorage.removeItem(this.TOKEN_KEY);
  }

  getRefreshToken(): string | null{
    return sessionStorage.getItem(this.REFRESH_TOKEN_KEY);
  }
  storeRefreshToken(token:string){
    sessionStorage.setItem(this.REFRESH_TOKEN_KEY, token);
  }
  removeRefreshToken(){
    sessionStorage.removeItem(this.REFRESH_TOKEN_KEY);
  }
}
