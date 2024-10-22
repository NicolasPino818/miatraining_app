import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoints } from '../../models/apiEndpoints';
import { IExercise, IExerciseFilters, IExercisePage } from '../../models/interfaces';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  constructor(private http: HttpClient) { }

  getExerciseFilters(): Observable<IExerciseFilters>{
    return this.http.get<IExerciseFilters>(apiEndpoints.exercise+'/filters');
  }

  getExercisesPage(pagination: {pageNumber: number, pageSize?: number}, filters: {search: string, category: string, trainingType: string}): Observable<IExercisePage>{

    let params: HttpParams = new HttpParams();
    params = params.set("pageNumber", pagination.pageNumber.toString());

    if(filters.search != '') params = params.set("search", filters.search.normalize("NFD").replace(/[\u0300-\u036f]/g, ""));
    if(filters.category != 'ALL') params = params.set("category", filters.category);
    if(filters.trainingType != 'ALL') params = params.set("trainingType", filters.trainingType);

    return this.http.get<IExercisePage>(apiEndpoints.exercise, {
      params: params
    });
  }

  createExercise(data: FormData): Observable<any>{
    return this.http.post(apiEndpoints.exercise, data);
  }

  updateExercise(id:number,data: FormData): Observable<any>{
    return this.http.put(apiEndpoints.exercise+'/'+id, data);
  }

  deleteExercise(id:number): Observable<any>{
    return this.http.delete(apiEndpoints.exercise+'/'+id);
  }

}
