import { Phone } from '.'

export interface User {
  name: string
  password: string
  wishlist: Phone[] // esto seria una herencia de productos
  isLoggedIn?: boolean
}
