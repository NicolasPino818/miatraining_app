import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { accountOptions } from '../../../models/nav'; // Importa tus opciones de ejercicios si es necesario

@Component({
  selector: 'app-client-training-plan-view',
  standalone: true,
  imports: [NgFor],
  templateUrl: './client-training-plan-view.component.html',
  styleUrls: ['./client-training-plan-view.component.css']
})
export class ClientTrainingPlanViewComponent {
  // Definición de los días
  days = [
    'Ejercicios Lunes',
    'Ejercicios Martes',
    'Ejercicios Miércoles',
    'Ejercicios Jueves',
    'Ejercicios Viernes',
    'Ejercicios Sábado',
    'Ejercicios Domingo'
  ];
  
  // Objetos de ejercicios (puedes usar accountOptions también)
  exercises = [
    {
      name: 'Press de Pecho con Mancuernas',
      image: 'ruta/a/la/imagen.jpg',
    },
    {
      name: 'Sentadillas',
      image: 'ruta/a/la/imagen-sentadilla.jpg'
    },
    // Agrega más ejercicios aquí...
  ];
}
