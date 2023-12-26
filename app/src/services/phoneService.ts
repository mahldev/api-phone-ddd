import { Phone } from '@/models/phone'
import { PhoneResponse } from '@/models/endpointPhone'
import { createAddaptedPhone } from '@/adapters/phoneAdapter'

const API_URL = 'http://localhost:3000'

export async function getAllPhones(): Promise<Phone[]> {
  const getAllPhonesUrl = `${API_URL}/rest/api/phones`

  try {
    const response = await fetch(getAllPhonesUrl)

    if (!response.ok) {
      throw new Error(`Error fetching phones. Status: ${response.status}`)
    }

    const phones: PhoneResponse = await response.json()
    return phones.phones.map(createAddaptedPhone)
  } catch (error) {
    console.error('Error fetching phones:', error)
    throw error
  }
}
