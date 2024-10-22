import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ITrainingPlanExercise } from '../../../models/interfaces';
import { NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-exercise-info-modal',
  standalone: true,
  imports: [NgFor,NgIf,RouterLink],
  templateUrl: './exercise-info-modal.component.html',
  styleUrl: './exercise-info-modal.component.css'
})
export class ExerciseInfoModalComponent {
  @Input()
  exercise!:ITrainingPlanExercise|null;
  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();

  safeTutorialSrc!: SafeResourceUrl;  // Nueva variable para la URL sanificada

  constructor(private sanitizer: DomSanitizer) {}

  ngOnChanges() {
    if (this.exercise?.tutorialLink) {
      this.safeTutorialSrc = this.sanitizeUrl(this.exercise.tutorialLink);
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
