import type { User, Phone, ShoppingCartItem } from '@/models'
import { login as loginUser, doOrder as sendOrder } from '@/services'
import {
  createUser,
  resetUser,
  addToWishlist as addItems,
  removeFromWishlist as removeItem,
  addToShoppingCart as addItemsToCart,
  removeFromShoppingCart as removeItemFromCart,
  removeUnitFromShoppingCart as removeUnit,
  addUnitFromShoppingCart as addUnit,
  resetShoppingCart
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

  const isWishlistItem = (id: number) => user.wishlist.some(item => item.id === id)

  const addToShoppingCart = (items: ShoppingCartItem) => dispatch(addItemsToCart(items))

  const removeFromShoppingCart = (item: ShoppingCartItem) => dispatch(removeItemFromCart(item))

  const removeUnitFromShoppingCart = (item: ShoppingCartItem) => dispatch(removeUnit(item))

  const addUnitFromShoppingCart = (item: ShoppingCartItem) => dispatch(addUnit(item))

  const isOnShoppingCart = (id: number) => user.shoppingCart.some(item => item.item.id === id)

  const numberOfItemsOnCart = () => user.shoppingCart.length

  const numberOfItemsOnWishlist = () => user.wishlist.length

  const getUserWishList = () => user.wishlist

  const getUserShoppingCart = () => user.shoppingCart

  const clearShoppingCart = () => dispatch(resetShoppingCart())

  const doOrder = async (): Promise<boolean> => {
    const items = user.shoppingCart
    const userId = user.id

    return await sendOrder(userId, items)
  }

  const login = async (user: Omit<User, 'id'>): Promise<boolean> => {
    const validUser = await loginUser(user)
    validUser && create(validUser)
    return !!validUser
  }

  return {
    isLoggedIn,
    create,
    reset,
    addToWishlist,
    removeFromWishlist,
    isWishlistItem,
    login,
    addToShoppingCart,
    removeFromShoppingCart,
    removeUnitFromShoppingCart,
    addUnitFromShoppingCart,
    isOnShoppingCart,
    numberOfItemsOnCart,
    numberOfItemsOnWishlist,
    getUserWishList,
    getUserShoppingCart,
    doOrder,
    clearShoppingCart
  }

}

export default useUserActions

