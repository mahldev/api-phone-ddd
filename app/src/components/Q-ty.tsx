import { MinusIcon, PlusIcon } from '@/assets/icons'

const Qty = ({ children, removeQuantity, addQuantity }:
  { children: number, removeQuantity: () => void, addQuantity: () => void }) => {

  return (
    <div className='flex items-center justify-center gap-2'>
      <button onClick={removeQuantity}><MinusIcon /></button>
      <div className='flex items-center border-1 border-[#d9d9d9] rounded-md justify-center px-4 py-2'>
        {children}
      </div>
      <button onClick={addQuantity}><PlusIcon /></button>
    </div>
  )
}

export default Qty
