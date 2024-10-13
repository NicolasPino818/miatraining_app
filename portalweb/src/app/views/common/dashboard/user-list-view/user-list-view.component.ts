import { Component } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { UserService } from '../../../../services/users/user.service';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IRole, IUser } from '../../../../models/interfaces';
import { NgFor, NgIf } from '@angular/common';
import { UserProfileService } from '../../../../services/users/user-profile.service';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError, EMPTY } from 'rxjs';
import { RegisterUserFormModalComponent } from '../../../../components/user/register-user-form-modal/register-user-form-modal.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-list-view',
  standalone: true,
  imports: [NgIf, RouterLink,NgxPaginationModule, ReactiveFormsModule, NgFor, RegisterUserFormModalComponent],
  templateUrl: './user-list-view.component.html',
  styleUrl: './user-list-view.component.css'
})
export class UserListViewComponent{
  nextQueryPage: number = 0;
  lastQueryPage: number = 0;
  firstQueryDone: boolean = false;
  userList: IUser[] = [];
  currentPaginationPage: number = 1;
  itemsPerPage: number = 10;
  totalPages!:number; 
  requestEnd: boolean = false;
  filters: FormGroup = new FormGroup({
    search: new FormControl(''),
    role: new FormControl('ALL', [
      Validators.required
    ]),
  });
  roleList: IRole[] = [];
  currentUserEmail!: string;
  showCreateuserForm: boolean = false;
  constructor(private userService:UserService, private userProfileService: UserProfileService){
    this.userProfileService.userProfile$.subscribe((profileInfo)=>{
      if (profileInfo) {
        this.currentUserEmail = profileInfo.email;
        this.getRoleList();
      }
    })
  }

  getRoleList(){
    this.userService.getRoles().subscribe((roles)=>{
      this.roleList = roles;
      this.getUserList(this.nextQueryPage);
    });
  }

  getUserList(pageNumber: number): void {
    let role = this.filters.get('role')?.value as string;
    let search = this.filters.get('search')?.value as string;
    search = search.trim();

    if (this.userList.length == 0) this.requestEnd = false;

    this.userService.getUserPage({pageNumber: pageNumber}, {role, search}).pipe(
      catchError((error:HttpErrorResponse)=>{
        this.catchError(error);
        return EMPTY;
      })
    ).subscribe((userPage) => {
      this.firstQueryDone = true;
      if (userPage.users.length > 0) {
        userPage.users.forEach(user => {
          // Evitar agregar duplicados
          if (!this.userList.some(existingUser => existingUser.id === user.id)) {
            this.userList.push(user);
          }
        });
        this.filterLoggedUser(this.currentUserEmail);
        this.orderByIdAsc();
        // Actualizamos las páginas consultadas
        this.lastQueryPage = userPage.pageNumber;
        this.nextQueryPage = userPage.pageNumber + 1;
  
        // Actualizar el número total de páginas para la paginación
        this.totalPages = Math.ceil(this.userList.length / this.itemsPerPage);
      }
      this.requestEnd = true;
    });
  }

  filterLoggedUser(loggedUserEmail: string) {
    this.userList = this.userList.filter(user => user.email !== loggedUserEmail);
  }

  orderByIdAsc(){
    this.userList = this.userList.sort((a, b) => a.id - b.id);
  }

  onPageChange(page: number): void {
    this.currentPaginationPage = page;
    // Si estamos en la penúltima o última página visible, obtener más usuarios
    if (this.currentPaginationPage >= this.totalPages - 1) {
      this.getUserList(this.nextQueryPage);
    }
  }

  filter(){
    this.currentPaginationPage = 1;
    this.requestEnd = false;
    this.firstQueryDone = false;
    let role = this.filters.get('role')?.value as string;
    let search = this.filters.get('search')?.value as string;
    search = search.trim();

    this.userService.getUserPage({pageNumber: 0}, {role, search})
    .pipe(
      catchError((error:HttpErrorResponse)=>{
        this.catchError(error);
        return EMPTY;
      })
    ).subscribe((userPage) => {
      this.userList = userPage.users;
      this.filterLoggedUser(this.currentUserEmail);
      this.orderByIdAsc();
      // Actualizamos las páginas consultadas
      this.lastQueryPage = 0;
      this.nextQueryPage = 1;

      // Actualizar el número total de páginas para la paginación
      this.totalPages = Math.ceil(this.userList.length / this.itemsPerPage);
      this.requestEnd = true;
    });
  }

  catchError(error:HttpErrorResponse){
    alert("Estamos experimentando problemas al cargar los usuarios.");
  }

  openCreateuserForm(){
    this.showCreateuserForm = true;
  }

  closeCreateuserForm(){
    this.showCreateuserForm = false;
  }

}
