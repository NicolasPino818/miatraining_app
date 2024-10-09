const apiV1PathURL: string = 'http://localhost:8080/api/v1/';
export const apiEndpoints = {
    login: apiV1PathURL+'auth/login',
    refreshToken: apiV1PathURL+'auth/refresh-token',
    forgotPassword: apiV1PathURL+'auth/forgot-password',
    trainingPlan: apiV1PathURL+'training-plan',
    exercise: apiV1PathURL+'exercise',
    exerciseCategory: apiV1PathURL+'exercise/category',
    exerciseType: apiV1PathURL+'exercise/type',
    user: apiV1PathURL+'user'
}