import { Header } from '@/components'
import ItemPreview from '@/components/ItemPreview'
import { useUserActions } from '@/hooks'
import { Button, Input } from '@nextui-org/react'
import { useNavigate } from 'react-router-dom'
import { v4 as uuidv4 } from 'uuid'

const OrderPage = () => {
  const {
    getUserShoppingCart,
    doOrder,
    clearShoppingCart,
    isLoggedIn
  } = useUserActions()
  const items = getUserShoppingCart()
  const navigate = useNavigate()

  const handleOrder = async () => {
    !isLoggedIn() && navigate('/login')

    const success = await doOrder()
    success && clearShoppingCart()
  }

  const totalPayment = (() =>
    items.length > 0 ? items
      .map(item => item.item.price * item.quantity)
      .reduce((acc, curr) => acc + curr) : 0
  )()

  return (
    <>
      <Header />
      <main className='mt-10 grid grid-cols-2 m-auto w-[1230px] auto-rows-[700px]'>
        <section className='flex flex-col gap-20 pr-14 '>
          <h2 className='text-2xl font-medium'>Shopping Cart</h2>
          <div className='flex flex-col gap-4 overflow-hidden hover:overflow-auto'>
            {items.map((item, index) => {
              const lastItem = index === items.length - 1
              return (
                <div key={uuidv4()} className={lastItem ? '' : 'border-b-1 border-[#dcdcdc] pb-4'}>
                  <ItemPreview item={item.item} quantity={item.quantity} />
                </div>
              )
            })}
          </div>
        </section>

        <section className='flex flex-col px-14 py-10 border justify-between rounded-md border-[#dcdcdc]'>
          <div className='flex flex-col gap-10'>
            <h2 className='text-2xl font-medium'>Order Summary</h2>
            <div className='flex flex-col gap-8'>
              <div className='flex flex-col gap-2'>
                <p className='text-gray-600 font-medium'>Discount code / Promo code</p>
                <Input
                  type='text'
                  placeholder='Code'
                  radius='sm'
                  variant='bordered'
                  classNames={{
                    input: 'text-sm font-ligth',
                    inputWrapper: 'py-8 border-1 border-[#dcdcdc]',
                  }}
                />
              </div>
              <div className='flex flex-col gap-2'>
                <p className='text-gray-600 font-medium'>Your bonus card number</p>
                <Input
                  type='text'
                  placeholder='Enter Card Number'
                  endContent={<Button className='rounded-md bg-transparent border-1 border-black font-medium px-8'>Apply</Button>}
                  radius='sm'
                  variant='bordered'
                  classNames={{
                    input: 'text-sm font-ligth',
                    inputWrapper: 'py-8 border-1 border-[#dcdcdc]',
                  }}
                />
              </div>
            </div>
          </div>
          <div className='flex flex-col gap-5 justify-self-end'>
            <span className='flex justify-between'>
              <p className='text-lg font-medium text-black'>Total</p>
              <p className='text-lg'>{totalPayment.toFixed(2)}€</p>
            </span>
            <Button
              className='rounded-md bg-black text-white font-medium text-md py-8'
              size='lg'
              radius='none'
              onPress={handleOrder}
            >
              Checkout
            </Button>
          </div>
        </section>
      </main >
    </>
  )
}

export default OrderPage
