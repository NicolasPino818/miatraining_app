import { NgFor, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { IRole } from '../../../models/interfaces';
import { UserService } from '../../../services/users/user.service';

@Component({
  selector: 'app-register-user-form-modal',
  standalone: true,
  imports: [NgIf, NgFor, ReactiveFormsModule],
  templateUrl: './register-user-form-modal.component.html',
  styleUrl: './register-user-form-modal.component.css'
})
export class RegisterUserFormModalComponent {
  public submitted: boolean = false; //INDICA SI BOTON DEL FORMULARIO FUE APRETADO
  //LO UTILIZAMOS PARA DESACTIVAR EL BOTON DEL FORMULARIO MIENTRAS SE PROCESA LA REQUEST Y EVITAR SPAM AL BACKEND
  public btnDisabled: boolean = false; 
  public showLoader: boolean = false; //ACTIVA O DESACTIVA EL INDICADOR DE CARGA
  public responseMsg: string = ""; //MENSAJE DE RESPUESTA AL CLIENTE EN CASO DE ERROR
  public showResponseMsg: boolean = false; //ACTIVA O DESACTIVA EL MENSAJE DE ERROR

  @Input()
  roles!: IRole[];

  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();
  
  @Output()
  submitionFinished: EventEmitter<void> = new EventEmitter<void>();

  constructor(private userService:UserService){}

  closeModalEvent(){
    this.closeModal.emit();
  }

  submitionFinishedEvent(){
    this.submitionFinished.emit();
  }

}
