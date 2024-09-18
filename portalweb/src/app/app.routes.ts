import { Routes } from '@angular/router';
import { LoginViewComponent } from './views/auth/login-view/login-view.component';
import { ForgotPasswordViewComponent } from './views/auth/forgot-password-view/forgot-password-view.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { ClientTrainingPlanViewComponent } from './views/client/client-training-plan-view/client-training-plan-view.component';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'auth'
    },
    {
        //Todasl as rutas hijas quedan dentro de: auth/**
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
                //Todasl as rutas hijas quedan dentro de: cliente/**
                path: 'cliente',
                title: 'Plan de entrenamiento',
                children:[
                    {
                        path: 'plan-entrenamiento',
                        title: 'Plan de entrenamiento',
                        component: ClientTrainingPlanViewComponent
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'plan-entrenamiento'
                    }
                ]
            },
            
            {
                //Todasl as rutas hijas quedan dentro de: admin/**
                path: 'admin',
                title: 'Administración',
                children:[
                    {
                        path: 'clientes',
                        title: 'Vista Clientes',
                        //Cambiar este componente por el correspondiente despues
                        component: ClientTrainingPlanViewComponent
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'clientes'
                    }
                ]
            },
            {
                //Todasl as rutas hijas quedan dentro de: coach/**
                path: 'coach',
                title: 'Administración',
                children:[
                    {
                        path: 'clientes',
                        title: 'Vista Clientes',
                        //Cambiar este componente por el correspondiente despues
                        component: ClientTrainingPlanViewComponent
                    },
                    {
                        path: '',
                        pathMatch: 'prefix',
                        redirectTo: 'clientes'
                    }
                ]
            },
            {
                path: '',
                pathMatch: 'full',
                redirectTo: '/auth/login'
            }
        ]
    },
];
