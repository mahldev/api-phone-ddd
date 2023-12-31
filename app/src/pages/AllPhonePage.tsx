import { Header, PhoneCard } from '@/components'
import useUserActions from '@/hooks/useUserActions'
import { Phone } from '@/models'
import { getAllPhones } from '@/services'
import { Spinner } from '@nextui-org/react'
import { useEffect, useState } from 'react'

export function AllPhonePage() {
  const [loading, setLoading] = useState(true)
  const [phones, setPhones] = useState<Phone[]>()

  const {
    addToWishlist,
    removeFromWishlist,
    isWishlistItem,
    isLoggedIn
  } = useUserActions()

  useEffect(() => {
    async function fetchPhones() {
      const allPhones = await getAllPhones()
      setPhones(allPhones)
      setLoading(false)
    }

    fetchPhones()
  }, [])

  const handdleLike = (phone: Phone) => {
    if (isLoggedIn()) {
      const isOnWishList = isWishlistItem(phone.id)
      isOnWishList ? removeFromWishlist(phone) : addToWishlist([phone])
    }
  }

  return (
    <>
      <Header />
      <main className='flex items-center justify-center mt-2'>
        {loading ? (
          <Spinner />
        ) : (
          <section className='grid justify-center grid-cols-3 gap-4'>
            {phones?.map((phone) => {
              const isLiked = isWishlistItem(phone.id)
              const { images, name, price } = phone
              return (
                <PhoneCard
                  key={phone.id}
                  id={phone.id}
                  image={images[0]}
                  name={name}
                  price={price}
                  isLiked={isLiked}
                  handleLike={() => handdleLike(phone)}
                />
              )
            })}
          </section>
        )}
      </main>
    </>
  )
}
