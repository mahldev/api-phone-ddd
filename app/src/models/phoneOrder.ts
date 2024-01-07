import type { PhoneColor } from '@/models'

type PhoneOrder = {
  id: number
  name: string
  color: PhoneColor
  price: number
  storage: number
  imagePreview: string
}

export const isEqualPhoneOrder = (orderA: PhoneOrder, orderB: PhoneOrder): boolean => {
  return (
    orderA.id === orderB.id &&
    orderA.color.color.name === orderB.color.color.name &&
    orderA.storage === orderB.storage
  );
};

export default PhoneOrder
