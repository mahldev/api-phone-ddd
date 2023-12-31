import { useEffect, useState } from 'react'
import useUserActions from './useUserActions'
import type { Phone } from '@/models'

const useOnWishlist = ({ phone }: { phone: Phone | null }) => {
  const { addToWishlist, removeFromWishlist, isWishlistItem } = useUserActions()

  const [onWishlist, setOnWishlist] = useState<boolean>()

  useEffect(() => {
    if (phone !== null) {
      const isOnList = isWishlistItem(phone?.id)
      setOnWishlist(isOnList)
    }
  }, [onWishlist, phone])

  const handleWishlist = (phone: Phone | null) => {
    if (phone !== null) {
      const isOnList = isWishlistItem(phone.id)
      isOnList ? removeFromWishlist(phone) : addToWishlist([phone])
    }
  }

  return { onWishlist, handleWishlist }
}

export default useOnWishlist
