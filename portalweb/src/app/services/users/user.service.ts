import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { IRole, IUserDetailsFormOptions, IUserDetailsFormSubmission, IUserPage } from '../../models/interfaces';
import { apiEndpoints } from '../../models/apiEndpoints';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUserPage(pagination: {pageNumber: number, pageSize?: number}, filters: {role: string, search: string}): Observable<IUserPage>{

    let params: HttpParams = new HttpParams();
    params = params.set("pageNumber", pagination.pageNumber.toString());
    if(pagination.pageSize) params = params.set("pageSize", pagination.pageSize.toString());
    if(filters.role != 'ALL') params = params.set("role", filters.role);
    if(filters.search != '') params = params.set("search", filters.search);

    return this.http.get<IUserPage>(apiEndpoints.user, {
      params: params
    });
  }

  getRoles(): Observable<IRole[]>{
    return this.http.get<IRole[]>(apiEndpoints.user+'/role');
  }

  getUserDetailsFormOptions(): Observable<IUserDetailsFormOptions>{
    return this.http.get<IUserDetailsFormOptions>(apiEndpoints.user+'/user-details-options');
  }

  saveUserDetails(email: string, body: FormData): Observable<any> {
    return this.http.post(apiEndpoints.user + '/' + email + '/user-details', body);
  }
}
