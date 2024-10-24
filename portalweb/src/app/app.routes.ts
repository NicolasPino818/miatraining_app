import { Routes } from '@angular/router';
import { LoginViewComponent } from './views/auth/login-view/login-view.component';
import { ForgotPasswordViewComponent } from './views/auth/forgot-password-view/forgot-password-view.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { ClientTrainingPlanViewComponent } from './views/client/client-training-plan-view/client-training-plan-view.component';
import { ClientNutritionPlanViewComponent } from './views/client/client-nutrition-plan-view/client-nutrition-plan-view.component';
import { ExerciseGuideViewComponent } from './views/client/exercise-guide-view/exercise-guide-view.component';
import { ProgressTrackingViewComponent } from './views/client/progress-tracking-view/progress-tracking-view.component';
import { AccountSettingsViewComponent } from './views/common/settings/account-settings-view/account-settings-view.component';
import { NotFoundViewComponent } from './views/not-found-view/not-found-view.component';
import { userURLs } from './models/nav';
import { ResetPasswordViewComponent } from './views/common/settings/reset-password-view/reset-password-view.component';
import { ProfileInfoViewComponent } from './views/common/settings/profile-info-view/profile-info-view.component';
import { NotificationOptionsViewComponent } from './views/common/settings/notification-options-view/notification-options-view.component';
import { ContactCoachViewComponent } from './views/client/contact-coach-view/contact-coach-view.component';
import { FormViewComponent } from './views/client/form-view/form-view.component';
import { isUserLogedGuard } from './guards/login-guard/is-user-loged.guard';
import { isClientGuard } from './guards/client-guard/is-client.guard';
import { isAdminGuard } from './guards/admin-guard/is-admin.guard';
import { UserListViewComponent } from './views/common/dashboard/user-list-view/user-list-view.component';
import { EditExerciseLibraryViewComponent } from './views/common/dashboard/edit-exercise-library-view/edit-exercise-library-view.component';
import { EditTrainingPlanViewComponent } from './views/common/dashboard/edit-training-plan-view/edit-training-plan-view.component';
import { ObjectivesFormComponent } from './components/objectives-form/objectives-form.component';
import { firstLoginGuard } from './guards/first-login/first-login.guard';
import { notFirstLoginGuard } from './guards/not-first-login/not-first-login.guard';
import { isEnabledGuard } from './guards/is-enabled/is-enabled.guard';
import { TrainingPlanListViewComponent } from './views/common/dashboard/training-plan-list-view/training-plan-list-view.component';
import { SingleUserViewComponent } from './views/common/dashboard/single-user-view/single-user-view.component';


export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'auth'
    },
    {
        path: 'registro-formulario-inicial',
        title: 'Formulario Inicial',
        canActivate: [isUserLogedGuard, isClientGuard, firstLoginGuard, isEnabledGuard],
        component: ObjectivesFormComponent
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
        canActivate: [isUserLogedGuard],
        children: [
            {
                path: userURLs.clientBaseUrl,
                title: 'Cliente',
                canActivate: [isClientGuard, notFirstLoginGuard, isEnabledGuard],
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
                canActivate: [isAdminGuard, notFirstLoginGuard, isEnabledGuard],
                children: [
                    {
                        path: 'planes-entrenamiento',
                        title: 'Planes de Entrenamiento',
                        component: TrainingPlanListViewComponent
                    },
                    {
                        path: 'planes-entrenamiento/:planID',
                        title: 'Plan de Entrenamiento',
                        component: EditTrainingPlanViewComponent
                    },
                    {
                        path: 'usuarios',
                        title: 'Vista Usuarios',
                        component: UserListViewComponent
                    },
                    {
                        path: 'usuarios/:usuarioID',
                        title: 'Ver usuario',
                        component: SingleUserViewComponent
                    },
                    {
                        path: 'ejercicios',
                        title: 'Biblioteca de ejercicios',
                        component: EditExerciseLibraryViewComponent
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
        component: NotFoundViewComponent
    },

];