import { ShoppingCartItem, User, createOrder } from '@/models'
import { fromUserEnpointToUser, fromUserToEndpointUser } from '@/adapters'

const API_URL = '/api/rest/api'

export async function login(user: Omit<User, 'id'>): Promise<User | null> {
  const endpointUser = fromUserToEndpointUser(user)
  const loginURL = `${API_URL}/login`
  const options = {
    method: "POST",
    body: JSON.stringify(endpointUser),
    headers: {
      'Content-type': 'application/json'
    }
  }

  const response = await fetch(loginURL, options)

  if (response.status !== 200) return null

  const json = await response.json()
  return fromUserEnpointToUser(json)
}


export async function doOrder(userId: number, items: ShoppingCartItem[]): Promise<boolean> {
  const order = createOrder(userId, items)
  const orderURL = `${API_URL}/orders`
  const options = {
    method: "POST",
    body: JSON.stringify(order),
    headers: {
      'Content-type': 'application/json'
    }
  }

  const response = await fetch(orderURL, options)

  return response.status === 201
}
