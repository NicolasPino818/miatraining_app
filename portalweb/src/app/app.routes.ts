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
                path: 'cliente',
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
                        path: 'mi-cuenta',
                        title: 'Mi cuenta',
                        component: AccountSettingsViewComponent
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'plan-entrenamiento'
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'alimentacion'
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'guia-ejercicios'
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'avances-progreso'
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'mi-cuenta'
                    }
                ]
            },
            {
                path: 'admin',
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
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'usuarios'
                    }
                ]
            }
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
