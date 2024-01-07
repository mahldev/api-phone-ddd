import { ShoppingCartItem } from '@/models'
import { Image } from '@nextui-org/react'
import { capitalizeWord } from '@/util'
import { DeleteIcon } from '@/assets/icons'
import { Qty } from '@/components'
import { useUserActions } from '@/hooks'
import { formatSize } from '@/util'

const ItemPreview = ({ item, quantity }: ShoppingCartItem) => {
  const {
    removeUnitFromShoppingCart,
    addToShoppingCart,
    removeFromShoppingCart
  } = useUserActions()

  const handleRemoveUnit = () => { removeUnitFromShoppingCart({ item, quantity }) }

  const handleAdd = () => { addToShoppingCart({ item, quantity }) }

  const handleRemoveItem = (item: ShoppingCartItem) => { removeFromShoppingCart(item) }

  return (
    <div className='flex items-center justify-between '>
      <Image width={150} height={150} src={`/images/${item.imagePreview}`} />
      <div className='font-medium text-md'>
        <p>{item.name}</p>
        <p>{`${formatSize(item.storage)} ${capitalizeWord(item.color.commercialName)}`}</p>
      </div>
      <div className='flex items-center gap-8'>
        <Qty removeQuantity={handleRemoveUnit} addQuantity={handleAdd}>{quantity}</Qty>
        <p className='text-xl'>{item.price}€</p>
        <button className='bg-transparent' onClick={() => handleRemoveItem({ item, quantity })}><DeleteIcon /></button>
      </div>
    </div >
  )
}

export default ItemPreview
