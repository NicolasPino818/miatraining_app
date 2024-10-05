import { Routes } from '@angular/router';
import { LoginViewComponent } from './views/auth/login-view/login-view.component';
import { ForgotPasswordViewComponent } from './views/auth/forgot-password-view/forgot-password-view.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { ClientTrainingPlanViewComponent } from './views/client/client-training-plan-view/client-training-plan-view.component';
import { ClientNutritionPlanViewComponent } from './views/client/client-nutrition-plan-view/client-nutrition-plan-view.component';
import { ExerciseGuideViewComponent } from './views/client/exercise-guide-view/exercise-guide-view.component';
import { ProgressTrackingViewComponent } from './views/client/progress-tracking-view/progress-tracking-view.component';
import { AccountSettingsViewComponent } from './views/client/account-settings-view/account-settings-view.component';
import { ClientListViewComponent } from './views/admin/client-list-view/client-list-view.component';
import { RegisterUsersViewComponent } from './views/admin/register-users-view/register-users-view.component';
import { NotFoudViewComponent } from './views/not-foud-view/not-foud-view.component';
import { EditTrainingPlanViewComponent } from './views/admin/edit-training-plan-view/edit-training-plan-view.component';
import { userURLs } from './models/nav';
import { ResetPasswordViewComponent } from './views/common/settings/reset-password-view/reset-password-view.component';
import { ProfileInfoViewComponent } from './views/common/settings/profile-info-view/profile-info-view.component';
import { NotificationOptionsViewComponent } from './views/common/settings/notification-options-view/notification-options-view.component';
import { ContactCoachViewComponent } from './views/client/contact-coach-view/contact-coach-view.component';
import { FormViewComponent } from './views/client/form-view/form-view.component';


export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'auth'
    },
    {
        path: 'auth',
        children: [
            {
                path: 'login',
                component: LoginViewComponent,
                title: 'Iniciar Sesión'
            },
            {
                path: 'recuperar-contrasena',
                component: ForgotPasswordViewComponent,
                title: 'Recuperar Contraseña'
            },
            {
                path: '',
                pathMatch: 'prefix',
                redirectTo: 'login'
            }
        ]
    },
    {
        path: 'dashboard',
        component: DashboardComponent,
        title: 'Dashboard',
        children: [
            {
                path: userURLs.clientBaseUrl,
                title: 'Cliente',
                children: [
                    {
                        path: 'plan-entrenamiento',
                        title: 'Plan de entrenamiento',
                        component: ClientTrainingPlanViewComponent
                    },
                    {
                        path: 'alimentacion',
                        title: 'Alimentación',
                        component: ClientNutritionPlanViewComponent
                    },
                    {
                        path: 'guia-ejercicios',
                        title: 'Guía de ejercicios',
                        component: ExerciseGuideViewComponent
                    },
                    {
                        path: 'avances-progreso',
                        title: 'Avances y progreso',
                        component: ProgressTrackingViewComponent
                    },
                    {
                        path: userURLs.settingsBaseUrl,
                        title: 'Mi cuenta',
                        component: AccountSettingsViewComponent
                    },
                    {
                        path: userURLs.settingsBaseUrl+'/mi-perfil',
                        title: 'información del Perfil',
                        component: ProfileInfoViewComponent
                    },
                    {
                        path: userURLs.settingsBaseUrl+'/cambiar-contrasena',
                        title: 'Cambio de contraseña',
                        component: ResetPasswordViewComponent
                    },
                    {
                        path: userURLs.settingsBaseUrl+'/notificaciones',
                        title: 'Notificaciones',
                        component: NotificationOptionsViewComponent
                    },
                    {
                        path: userURLs.settingsBaseUrl+'/formulario',
                        title: 'Formulario',
                        component: FormViewComponent
                    },
                    {
                        path: userURLs.settingsBaseUrl+'/contacto-coach',
                        title: 'Contacto Coach',
                        component: ContactCoachViewComponent
                    },
                    {
                        path: '',
                        pathMatch: 'full',
                        redirectTo: 'plan-entrenamiento'
                    }
                ]
            },
            {
                path: userURLs.adminBaseUrl,
                title: 'Administración',
                children: [
                    {
                        path: 'usuarios',
                        title: 'Vista Usuarios',
                        component: ClientListViewComponent
                    },
                    {
                        path: 'registro',
                        title: 'Registro Usuarios',
                        component: RegisterUsersViewComponent
                    },
                    {
                      path: 'plan-entrenamiento',
                      title: 'Editar Plan de Entrenamiento',
                      component: EditTrainingPlanViewComponent
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'usuarios'
                    }
                ]
            },
            {
                path: '',
                pathMatch: 'full',
                redirectTo: '/auth/login'
            },
        ]
    },
    {
        path: '',
        pathMatch: 'full',
        redirectTo: '/auth/login'
    },
    {
        path: '**',
        pathMatch: 'prefix',
        component: NotFoudViewComponent
    },

];
