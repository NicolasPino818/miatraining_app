import { Injectable } from '@angular/core';
import {jwtDecode} from 'jwt-decode';
import { IDecodedAccessTokenInfo, IDecodedRefreshTokenInfo } from '../../models/interfaces';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor() { }
  
  getDecodedAccessToken(token: string): IDecodedAccessTokenInfo{
    try {
      return jwtDecode(token);
    } catch(Error) {
      return {
          sub: "",
          exp: 0,
          iat: 0,
          authorization: "",
          name: ""
      };
    }
  }

  getDecodedRefreshToken(token: string): IDecodedRefreshTokenInfo{
    try {
      return jwtDecode(token);
    } catch(Error) {
      return {
        sub: "",
        exp: 0,
        iat: 0
      };
    }
  }
}
