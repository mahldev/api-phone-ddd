import { User } from '@/models'
import { fromUserToEndpointUser } from '@/adapters'

const API_URL = 'http://localhost:3000'
const CODE_TO_VALID_USER = 200

export async function login(user: User) {

  const endpointUser = fromUserToEndpointUser(user)
  const loginURL = `${API_URL}/rest/api/login`

  const response = await fetch(loginURL, {
    method: "POST",
    body: JSON.stringify(endpointUser)
  })

  return response.status === CODE_TO_VALID_USER
}

