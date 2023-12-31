import { PhoneColor } from "."

export interface PhoneResponse {
  phones: EndpointPhone[]
}

export interface EndpointPhone {
  brandId: string
  colors: PhoneColor[]
  id: number
  images: string[]
  name: string
  price: number
  storagesSizes: number[]
}
