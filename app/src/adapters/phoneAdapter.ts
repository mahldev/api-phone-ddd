import { EndpointPhone } from '@/models/endpointPhone.ts'
import { Phone } from '@/models/phone.ts'

export function createAddaptedPhone(phone: EndpointPhone): Phone {
  const formattedPhone: Phone = {
    brandId: phone.brandId,
    colors: phone.colors,
    id: phone.id,
    images: phone.images,
    name: phone.name,
    price: phone.price,
    storagesSizes: phone.storagesSizes,
  }

  return formattedPhone
}
