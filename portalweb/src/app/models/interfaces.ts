export interface IAuthenticationRequest{
    email: string,
    password: string
}

export interface IAuthenticationResponse{
    access_token: string,
    refresh_token: string
}

export interface IDecodedAccessTokenInfo{
    authorization: string,
    exp: number,
    iat: number,
    name: string,
    sub: string
}

export interface IDecodedRefreshTokenInfo{
    exp: number,
    iat: number,
    sub: string
}

export interface IForgotPasswordStepStatus{
    success: boolean
}

export interface INavModel{
    linkName: string,
    icon: string,
    iconType: string,
    link: string
}


export interface ITrainingPlan{
    planID: number,
    planCreator: any,
    creationDate: string,
    planDays: any
}

export interface ITrainingPlanDay{
    id: number,
    dayNumber: number,
    exercises: ITrainingPlanExercise[]
}

export interface ITrainingPlanExercise{
    routineID: number,
    exerciseID: number,
    series: number,
    reps: number,
    restMinutes: number,
    exerciseName: string,
    tutorialLink: string,
    imageLink: string,
    description: string,
    categories: IExerciseCategory[]
}

export interface IExerciseCategory{
    id: number,
    categoryName: string,
}
