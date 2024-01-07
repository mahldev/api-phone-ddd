import type { User, Phone, ShoppingCartItem } from '@/models'
import { isEqualPhoneOrder } from '@/models'
import { PayloadAction, createSlice } from '@reduxjs/toolkit'

export const userDefaultState: User = {
  id: 0,
  name: '',
  password: '',
  wishlist: [],
  isLoggedIn: false,
  shoppingCart: []
}

const initialState: User = (() => {
  const storageState = localStorage.getItem('__user_state__')
  return storageState ? JSON.parse(storageState) : userDefaultState
})()

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    createUser: (_, action: PayloadAction<User>) => ({ ...action.payload, isLoggedIn: true }),
    resetUser: () => userDefaultState,
    addToWishlist: (state, action: PayloadAction<Phone[]>) => ({
      ...state,
      wishlist: [...state.wishlist, ...action.payload],
    }),
    removeFromWishlist: (state, action: PayloadAction<Phone>) => ({
      ...state,
      wishlist: state.wishlist.filter(item => item.id !== action.payload.id),
    }),
    addToShoppingCart: (state, action: PayloadAction<ShoppingCartItem>) => {
      const itemIndex = state.shoppingCart.findIndex(item => isEqualPhoneOrder(action.payload.item, item.item))

      if (itemIndex !== -1) {
        state.shoppingCart[itemIndex].quantity += 1
        return state
      }

      return { ...state, shoppingCart: [...state.shoppingCart, action.payload] }
    },
    removeFromShoppingCart: (state, action: PayloadAction<ShoppingCartItem>) => ({
      ...state,
      shoppingCart: state.shoppingCart.filter(item => !isEqualPhoneOrder(item.item, action.payload.item)),
    }),
    removeUnitFromShoppingCart: (state, action: PayloadAction<ShoppingCartItem>) => {
      const itemIndex = state.shoppingCart.findIndex(item => isEqualPhoneOrder(action.payload.item, item.item))

      if (itemIndex !== -1) {
        state.shoppingCart[itemIndex].quantity = Math.max(1, state.shoppingCart[itemIndex].quantity - 1)
        return state
      }

      return state
    },
    addUnitFromShoppingCart: (state, action: PayloadAction<ShoppingCartItem>) => {
      const itemIndex = state.shoppingCart.findIndex(item => isEqualPhoneOrder(item.item, action.payload.item))

      if (itemIndex !== -1) {
        state.shoppingCart[itemIndex].quantity += 1
      }

      return state
    },
    resetShoppingCart: (state) => ({ ...state, shoppingCart: [] }),
  },
})

export const {
  createUser,
  resetUser,
  addToWishlist,
  removeFromWishlist,
  addToShoppingCart,
  removeFromShoppingCart,
  removeUnitFromShoppingCart,
  addUnitFromShoppingCart,
  resetShoppingCart
} = userSlice.actions

export default userSlice.reducer
