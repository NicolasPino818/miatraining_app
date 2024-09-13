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