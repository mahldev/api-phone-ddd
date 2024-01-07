import { User, Phone, ShoppingCartItem } from '@/models'

type UserEndpoint = {
  id: number
  userName: string
  password: string
}

export const fromUserEnpointToUser = (userEndpoint: UserEndpoint): User => {
  const emptyPhoneArray: Phone[] = []
  const emptyShoppingCartArray: ShoppingCartItem[] = []

  const formattedUser: User = {
    id: userEndpoint.id,
    name: userEndpoint.userName,
    password: userEndpoint.password,
    wishlist: emptyPhoneArray,
    shoppingCart: emptyShoppingCartArray
  }

  return formattedUser
}

export const fromUserToEndpointUser = (user: Omit<User, 'id'>): UserEndpoint => {

  const formattedUser: UserEndpoint = {
    id: 0,
    userName: user.name,
    password: user.password
  }

  return formattedUser
}

