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

export async function getPhone({ phoneId }: { phoneId: string }): Promise<Phone> {
  const getPhoneUrl = `${API_URL}/rest/api/phones/${phoneId}`

  try {
    const response = await fetch(getPhoneUrl)

    if (!response.ok) {
      throw new Error(`Error fetching phone. Status: ${response.status}`)
    }

    const phone: Phone = await response.json()
    return createAddaptedPhone(phone)
  } catch (error) {
    console.error('Error fetching phone:', error)
    throw error
  }
}
