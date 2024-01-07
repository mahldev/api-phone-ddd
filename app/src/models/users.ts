import type { Phone, ShoppingCartItem } from '.'

export interface User {
  id: number
  name: string
  password: string
  wishlist: Phone[]
  isLoggedIn?: boolean
  shoppingCart: ShoppingCartItem[]
}
