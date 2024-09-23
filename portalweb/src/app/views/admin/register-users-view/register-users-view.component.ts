import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register-users-view',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, RouterLink],
  templateUrl: './register-users-view.component.html',
  styleUrl: './register-users-view.component.css'
})
export class RegisterUsersViewComponent {

  public submitted: boolean = false; //INDICA SI BOTON DEL FORMULARIO FUE APRETADO
  //LO UTILIZAMOS PARA DESACTIVAR EL BOTON DEL FORMULARIO MIENTRAS SE PROCESA LA REQUEST Y EVITAR SPAM AL BACKEND
  public btnDisabled: boolean = false; 
  public showLoader: boolean = false; //ACTIVA O DESACTIVA EL INDICADOR DE CARGA
  public responseMsg: string = ""; //MENSAJE DE RESPUESTA AL CLIENTE EN CASO DE ERROR
  public showResponseMsg: boolean = false; //ACTIVA O DESACTIVA EL MENSAJE DE ERROR

}
