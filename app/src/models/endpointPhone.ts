export interface PhoneResponse {
  phones: EndpointPhone[]
}

export interface EndpointPhone {
  brandId: string
  colors: string[]
  id: number
  images: string[]
  name: string
  price: number
  storagesSizes: number[]
}
