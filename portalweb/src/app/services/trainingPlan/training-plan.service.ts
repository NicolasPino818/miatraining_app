import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ITrainingPlan } from '../../models/interfaces';
import { apiEndpoints } from '../../models/apiEndpoints';

@Injectable({
  providedIn: 'root'
})
export class TrainingPlanService {

  constructor(private http: HttpClient) {
  }

  getPlanByPlanID(id: string | number): Observable<ITrainingPlan>{
    return this.http.get<ITrainingPlan>(apiEndpoints.trainingPlan+"/"+id);
  }

  getPlanByEmail(email: string): Observable<ITrainingPlan>{
    return this.http.get<ITrainingPlan>(apiEndpoints.trainingPlan+"/by-email/"+email);
  }

  updateExerciseRoutine(routineID: number, updatedRoutine: any): Observable<any> {
    return this.http.put(`${apiEndpoints.trainingPlan}/routine/${routineID}`, updatedRoutine);
  }

}
