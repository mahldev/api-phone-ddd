import { ShoppingCartItem } from "."

type Order = {
  userId: number,
  items: OrderItem[]

}

export type OrderItem = {
  phoneId: number,
  color: {
    name: string
  },
  storageSize: number,
  quantity: number
}

const itemsShoppingCartToOrderItem = (item: ShoppingCartItem): OrderItem => {
  return {
    phoneId: item.item.id,
    color: item.item.color.color,
    storageSize: item.item.storage,
    quantity: item.quantity
  }
}

export const createOrder = (userId: number, itemsShoppingCart: ShoppingCartItem[]): Order => {
  const items = itemsShoppingCart.map(itemsShoppingCartToOrderItem)

  return {
    userId,
    items,
  }
}

export default Order
