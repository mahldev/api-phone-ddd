import { User, Phone } from '@/models'

type UserEndpoint = {
  userName: string
  passoword: string
}

const createAddaptedUser = (userEndpoint: UserEndpoint): User => {
  const emptyArray: Phone[] = []

  const formattedUser: User = {
    name: userEndpoint.userName,
    password: userEndpoint.passoword,
    wishlist: emptyArray,
  }

  return formattedUser
}

export default createAddaptedUser
