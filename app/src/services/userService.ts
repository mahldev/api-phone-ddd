import { User } from '@/models'
import { fromUserToEndpointUser } from '@/adapters'

const API_URL = 'http://localhost:3000/rest/api/login'
const CODE_TO_VALID_USER = 200

export async function login(user: User) {

  const endpointUser = fromUserToEndpointUser(user)
  const loginURL = `${API_URL}`
  const options = {
    method: "POST",
    body: JSON.stringify(endpointUser),
    headers: {
      'Content-type': 'application/json'
    }
  }

  const response = await fetch(loginURL, options)

  return response.status === CODE_TO_VALID_USER
}
