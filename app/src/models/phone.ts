import { PhoneColor } from '@/models'

export interface Phone {
  brandId: string
  colors: PhoneColor[]
  id: number
  images: string[]
  name: string
  price: number
  storagesSizes: number[]
}

