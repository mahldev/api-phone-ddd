import { Phone } from "./phone.ts"

export interface Brand {
  id: number
  name: string
  phones: Phone[]
}

/*
{
  "id": 1,
  "name": "Apple",
  "phones": [
    {
      "brandId": 1,
      "colors": [
        "azul"
      ],
      "id": 4,
      "images": [
        "image1, image3, image3"
      ],
      "name": "myPhone2",
      "price": 1000,
      "storagesSizes": [
        128,
        256,
        1000,
        64
      ]
    },
    {
      "brandId": 1,
      "colors": [
        "blue titanium",
        "white titanium",
        "natural titanium",
        "black titanium"
      ],
      "id": 1,
      "images": [
        "apple-iphone-15-pro-max-black.webp",
        "apple-iphone-15-pro-max-gray.webp",
        "apple-iphone-15-pro-max-white.webp",
        "apple-iphone-15-pro-max-blue.webp"
      ],
      "name": "iPhone 15 Pro Max",
      "price": 1469.99,
      "storagesSizes": [
        128,
        256,
        512
      ]
    }
  ]
}
*/