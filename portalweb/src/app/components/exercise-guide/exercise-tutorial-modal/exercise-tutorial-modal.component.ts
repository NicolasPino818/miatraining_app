import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IExercise } from '../../../models/interfaces';  
import { NgFor, NgIf } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-exercise-tutorial-modal',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './exercise-tutorial-modal.component.html',
  styleUrls: ['./exercise-tutorial-modal.component.css']
})
export class ExerciseTutorialModalComponent {

  @Input()
  exercise!: IExercise | null;  // Recibe el ejercicio seleccionado
  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();

  safeTutorialSrc!: SafeResourceUrl;  // Nueva variable para la URL sanificada

  constructor(private sanitizer: DomSanitizer) {}

  ngOnChanges() {
    if (this.exercise?.tutorialSrc) {
      this.safeTutorialSrc = this.sanitizeUrl(this.exercise.tutorialSrc);
    }
  }
  
  sanitizeUrl(url: string): SafeResourceUrl {
    // Si la URL ya contiene "embed", la consideramos segura directamente
    if (this.isEmbedUrl(url)) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(url);
    }
  
    // Si no es una URL de "embed", extraemos el videoId y creamos la URL de embed
    const videoId = this.extractVideoId(url);
    if (videoId) {
      const embedUrl = `https://www.youtube.com/embed/${videoId}`;
      return this.sanitizer.bypassSecurityTrustResourceUrl(embedUrl);
    }
  
    // Si no se puede extraer el videoId, devolvemos la URL original por seguridad
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
  
  isEmbedUrl(url: string): boolean {
    // Verifica si la URL ya es de tipo embed
    return url.includes('youtube.com/embed/');
  }
  
  extractVideoId(url: string): string | null {
    // Extraemos el videoId de URLs estándar de YouTube o de youtu.be
    const regex = /(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/;
    const matches = url.match(regex);
    return matches ? matches[1] : null;
  }
  

  closeModalEvent(){
    this.closeModal.emit();
  }



}
