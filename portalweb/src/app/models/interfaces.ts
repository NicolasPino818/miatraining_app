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
  fullName: string,
  sub: string,
  firstLogin: boolean | null,
  enabled: boolean | null,
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

export interface IResetPassword{
  currentPassword: string,
  newPassword: string,
  repeatPassword: string
}

export interface IProfileInfo{
  name: string,
  surname: string,
  fullName: string,
  email: string,
  pictureUrlString: string,
  registrationDate: string,
}

export interface IRole{
  id: number,
  role: string
}

export interface IUserPage{
  users: IUser[],
  pageNumber:number,
  pageSize:number,
}

export interface IUser{
  id: number,
  fullName: string,
  email: string,
  enabled: boolean,
  firstLogin: boolean,
  registrationDate: string,
  role: string,
}

export interface IResetPassword{
    currentPassword: string,
    newPassword: string,
    repeatPassword: string
}

export interface IProfileInfo{
    name: string,
    surname: string,
    fullName: string,
    email: string,
    pictureUrlString: string,
    registrationDate: string,
}

export interface IRole{
    id: number,
    role: string
}

export interface IUserPage{
    users: IUser[],
    pageNumber:number,
    pageSize:number,
}

export interface IUser{
    id: number,
    fullName: string,
    email: string,
    enabled: boolean,
    firstLogin: boolean,
    registrationDate: string,
    role: string,
}

export interface IUserDetailsFormOptions{
  bodyType: IbodyType[],
  objective: Iobjective[],
  experience: Iexperience[],
  trainingType: ItrainingType[],
  diet: Idiet[],
}

export interface IbodyType{
  id: number,
  bodyType: string,
  description: string,
}

export interface Iobjective{
  id: number,
  objective: string,
  description: string,
}

export interface Iexperience{
  id: number,
  experience: string,
  description: string,
}

export interface ItrainingType{
  id: number,
  type: string,
}

export interface Idiet{
  id: number,
  diet: string,
}

export interface IUserDetailsFormSubmission {
  bodyTypeID: number,
  objectiveID: number,
  experienceID: number,
  trainingTypeID: number,
  dietID: number,
  gender: boolean,
  age: number,
  height: number,
  wight: number,
}