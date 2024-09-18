const apiV1PathURL: string = 'http://localhost:8080/api/v1/';
export const apiEndpoints = {
    login: apiV1PathURL+'auth/login',
    refreshToken: apiV1PathURL+'auth/refresh-token',
    forgotPassword: apiV1PathURL+'auth/forgot-password/verify',
}