export const userURLs ={
    clientBaseUrl: 'cliente',
    adminBaseUrl: 'admin',
    coachBaseUrl: 'coach',
    settingsBaseUrl: 'mi-cuenta'
}
export const navLinks = {
    clientNavLinks: [
        {
            linkName: 'PLAN DE ENTRENAMIENTO',
            icon: 'bx bx-dumbbell',
            iconType: 'BOXICON', 
            link: userURLs.clientBaseUrl+'/plan-entrenamiento'
        },
        {
            linkName: 'REGISTRO DE ALIMENTACIÓN',
            icon: 'bx bx-bowl-rice',
            iconType: 'BOXICON', 
            link: userURLs.clientBaseUrl+'/alimentacion'
        },
        {
            linkName: 'GUÍA DE EJERCICIOS',
            icon: 'bx bx-search',
            iconType: 'BOXICON', 
            link: userURLs.clientBaseUrl+'/guia-ejercicios'
        },
        {
            linkName: 'AVANCES Y PROGRESO',
            icon: 'bx bx-medal',
            iconType: 'BOXICON', 
            link: userURLs.clientBaseUrl+'/avances-progreso'
        },
        {
            linkName: 'Mi cuenta',
            icon: 'bx bx-user-circle',
            iconType: 'BOXICON', 
            link: userURLs.clientBaseUrl+'/mi-cuenta'
        }
    ],
    adminNavLinks: [
        {
            linkName: 'USUARIOS',
            icon: 'bx bxs-user-circle',
            iconType: 'BOXICON',
            link: userURLs.adminBaseUrl+'/usuarios',
        },
        {
            linkName: 'PLANES DE ENTRENAMIENTO',
            icon: 'bx bx-dumbbell',
            iconType: 'BOXICON', 
            link: userURLs.adminBaseUrl+'/plan-entrenamiento'
        },
        {
            linkName: 'BIBLIOTECA DE EJERCICIOS',
            icon: 'bx bx-run',
            iconType: 'BOXICON', 
            link: userURLs.adminBaseUrl+'/ejercicios'
        },
        {
            linkName: 'Mi cuenta',
            icon: 'bx bx-user-circle',
            iconType: 'BOXICON', 
            link: userURLs.adminBaseUrl+'/mi-cuenta'
        }
    ]
};

export const accountOptions = {
    clientOptions: [
        { name: 'Notificaciones', icon: 'bx bx-envelope', url: 'notificaciones' },
        { name: 'Cambiar Contraseña', icon: 'bx bx-lock-open-alt', url: 'cambiar-contrasena' },
        { name: 'Información del Perfil', icon: 'bx bx-user-circle', url: 'mi-perfil' },
        { name: 'Formulario', icon: 'bx bx-file', url: 'formulario' },
        { name: 'Contacto Coach', icon: 'bx bx-id-card', url: 'contacto-coach' }
    ],
    adminOptions: [
        { name: 'Notificaciones', icon: 'bx bx-envelope', url: 'notificaciones' },
        { name: 'Cambiar Contraseña', icon: 'bx bx-lock-open-alt', url: 'cambiar-contrasena' },
        { name: 'Información del Perfil', icon: 'bx bx-user-circle', url: 'mi-perfil' },
    ]
};