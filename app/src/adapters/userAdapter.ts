import { User, Phone } from '@/models'

type UserEndpoint = {
  userName: string
  passoword: string
}

export const fromUserEnpointToUser = (userEndpoint: UserEndpoint): User => {
  const emptyArray: Phone[] = []

  const formattedUser: User = {
    name: userEndpoint.userName,
    password: userEndpoint.passoword,
    wishlist: emptyArray,
  }

  return formattedUser
}

export const fromUserToEndpointUser = (user: User): UserEndpoint => {

  const formattedUser: UserEndpoint = {
    userName: user.name,
    passoword: user.password
  }

  return formattedUser
}

