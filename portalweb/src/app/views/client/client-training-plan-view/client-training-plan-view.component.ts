import { Component } from '@angular/core';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-client-training-plan-view',
  standalone: true,
  imports: [NgFor],
  templateUrl: './client-training-plan-view.component.html',
  styleUrls: ['./client-training-plan-view.component.css']
})
export class ClientTrainingPlanViewComponent {
  days = [
    'Ejercicios Lunes',
    'Ejercicios Martes',
    'Ejercicios Miércoles',
    'Ejercicios Jueves',
    'Ejercicios Viernes',
    'Ejercicios Sábado',
    'Ejercicios Domingo'
  ];
  
  exercises = [
    {
      name: 'Press de Pecho Sentado en maquina',
      image: 'ruta/a/la/imagen1.jpg'
    },
    {
      name: 'Press de pecho con cables',
      image: 'ruta/a/la/imagen2.jpg'
    },
    // Asegúrate de no tener ejercicios duplicados
    {
      name: 'Vuelos con mancuerna inclinado',
      image: 'ruta/a/la/imagen3.jpg'
    },
    {
      name: 'Vuelos sentado en máquina',
      image: 'ruta/a/la/imagen4.jpg'
    },
    {
      name: 'Elevaciones laterales',
      image: 'ruta/a/la/imagen5.jpg'
    },
    {
      name: 'Press de hombro con mancuernas',
      image: 'ruta/a/la/imagen6.jpg'
    },
    {
      name: 'Press de pecho inclinado con cables',
      image: 'ruta/a/la/imagen7.jpg'
    }
  ];

  constructor() {
    console.log(this.exercises);
  }
}
