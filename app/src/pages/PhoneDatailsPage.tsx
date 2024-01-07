import { BreadcrumbsPhoneDetails, Header, InfoCard, PhoneColorPicker, PhoneImage, Spinner } from '@/components';
import PhoneStoragePicker from '@/components/PhoneStoragePicker';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import type { PhoneColor, Phone, ShoppingCartItem } from '@/models';
import { Button, Skeleton } from '@nextui-org/react';
import { DeliveryTruckIcon, GuaranteedIcon, ShopIcon } from '@/assets/icons';
import { useUserActions, usePhone } from '@/hooks/';

export function PhoneDetailsView() {
  const { phoneId } = useParams()
  const { isLoggedIn,
    isWishlistItem,
    removeFromWishlist,
    addToWishlist,
    addToShoppingCart
  } = useUserActions()

  if (!phoneId) throw Error('Bad id')

  const { phone } = usePhone({ phoneId })
  const [isLoading, setIsLoading] = useState(true)
  const [selectedImage, setSelectedImage] = useState<string | undefined>()
  const [selectedColor, setSelectedColor] = useState<PhoneColor | undefined>()
  const [selectedStorage, setSelectedStorage] = useState<number | undefined>()
  const [onWishlist, setOnWishlist] = useState<boolean>(false)


  useEffect(() => {
    if (phone != null) {
      const isOnList = isLoggedIn() ? isWishlistItem(phone.id) : false

      setSelectedImage(phone?.images[0])
      setSelectedColor(phone?.colors[0])
      setSelectedStorage(phone?.storagesSizes[0])
      setOnWishlist(isOnList)

      setIsLoading(false)
    }
  }, [phone, onWishlist])

  const handleSelectedImage = (image: string) => setSelectedImage(image)

  const handleSelectedColor = (color: PhoneColor) => setSelectedColor(color)

  const handleSeletedStorage = (storage: number) => setSelectedStorage(storage)

  const handleWishlist = (phone: Phone | null) => {
    if (phone !== null) {
      const isOnList = isWishlistItem(phone.id)
      isOnList ? removeFromWishlist(phone) : addToWishlist([phone])
      setOnWishlist(!onWishlist)
    }
  }

  const createOrder = (
    { phone, selectedColor, selectedStorage }:
      { phone: Phone, selectedColor: PhoneColor, selectedStorage: number }) => {
    const shoppingCartItem: ShoppingCartItem = {
      item: {
        id: phone.id,
        name: phone.name,
        color: selectedColor,
        storage: selectedStorage,
        price: phone.price,
        imagePreview: phone.images[0]
      },
      quantity: 1
    }

    return shoppingCartItem
  }

  const handleShoppinCart = (shoppingCartItem: ShoppingCartItem) => {
    addToShoppingCart(shoppingCartItem)
  }

  return (
    <>
      <Header />
      <main className='flex flex-col m-auto max-w-[1230px]'>
        <header className='flex flex-col my-10 text-2xl'>
          <BreadcrumbsPhoneDetails
            phoneName={phone?.name}
          />
        </header>

        <section className='grid grid-cols-2 h-[600px] mt-10'>
          <div className='flex items-center justify-around'>
            <div className='flex flex-col gap-3'>
              {phone?.images.map(image => {
                const isSelected = selectedImage === image
                return isSelected ? ''
                  : <PhoneImage
                    key={image}
                    src={image}
                    width={100}
                    onClick={() => handleSelectedImage(image)}
                    className='cursor-pointer'
                  />
              })}
            </div>
            <PhoneImage src={selectedImage} width={500} />
          </div>
          <div className='flex flex-col text-left gap-6'>
            {isLoading
              ? <Skeleton className='h-3 w-20 rounded-lg' />
              : <h1 className='text-5xl font-medium'>{phone?.name}</h1>
            }
            {
              isLoading
                ? <Skeleton className='h-3 w-28 rounded-lg' />
                : <h2 className='text-3xl'>{`${phone?.price.toFixed()}€`}</h2>
            }
            <div className='flex gap-8 items-center'>
              <p>Select color: </p>
              <div className='flex gap-2'>
                {phone?.colors.map(c => {
                  const isSelected = c === selectedColor
                  return (
                    <PhoneColorPicker
                      key={c.color.name}
                      color={c.color}
                      commercialName={c.commercialName}
                      className={isSelected ? 'border-1 border-black' : ''}
                      onClick={() => handleSelectedColor(c)}
                    />
                  )
                })}
              </div>
            </div>
            <div className='flex gap-3'>
              {phone?.storagesSizes.map(storage => {
                const isSelected = storage === selectedStorage
                return (
                  <PhoneStoragePicker
                    key={storage}
                    storage={storage}
                    className={`bg-transparent border-1 font-medium rounded-md px-9 py-6
                      ${isSelected ? 'border-black' : 'border-gray-400 text-gray-500'}`}
                    onClick={() => handleSeletedStorage(storage)}
                  />
                )
              })}
            </div>
            <div className='w-full flex gap-5'>
              <Button
                className='px-20 py-7 bg-transparent font-medium border-1 border-gray-800 rounded-md w-64'
                size='lg'
                onPress={() => handleWishlist(phone)}
              >
                {`${onWishlist ? 'Remove' : 'Add'} to Wishlist `}
              </Button>
              {
                (phone !== null && selectedColor !== undefined && selectedStorage !== undefined) ? (
                  <Button
                    className='px-20 py-7 bg-black text-white font-medium border-1 rounded-md border-gray-800 w-64'
                    size='lg'
                    onPress={() => handleShoppinCart(createOrder({ phone, selectedColor, selectedStorage }))}
                  >
                    Add to Card
                  </Button>
                ) : (
                  <Button><Spinner /></Button>
                )
              }
            </div>
            <div className='flex gap-9 items-center'>
              <InfoCard icon={<DeliveryTruckIcon />} topText='Free Delivery' bottomText='1-2 day' />
              <InfoCard icon={<ShopIcon />} topText='In Stock' bottomText='today' />
              <InfoCard icon={<GuaranteedIcon />} topText='Guaranteed' bottomText='1 year' />
            </div>
          </div>
        </section>
      </main >
    </>
  )
}
