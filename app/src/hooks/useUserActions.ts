import type { User, Phone, PhoneId } from '@/models'
import {
  createUser,
  resetUser,
  addToWishlist as addItems,
  removeFromWishlist as removeItem,
} from '@/redux/users/slice'
import { useAppDispacth, useAppSelector } from './store.ts'

const useUserActions = () => {
  const dispatch = useAppDispacth()
  const user = useAppSelector(state => state.user)

  const isLoggedIn = () => user.isLoggedIn

  const create = (user: User) => dispatch(createUser(user))

  const reset = () => dispatch(resetUser())

  const addToWishlist = (phones: Phone[]) => dispatch(addItems(phones))

  const removeFromWishlist = (phone: Phone) => dispatch(removeItem(phone))

  const isWishlistItem = (id: PhoneId) => user.wishlist.some(item => item.id === id)

  const login = (user: User) => {

  }

  return { isLoggedIn, create, reset, addToWishlist, removeFromWishlist, isWishlistItem }
}

export default useUserActions

