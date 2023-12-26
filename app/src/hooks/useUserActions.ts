import type { User } from '@/models'
import { createUser } from '@/redux/users/slice'
import { useAppDispacth } from './store.ts'

const useUserActions = () => {
  const dispatch = useAppDispacth()

  const create = (user: User) => {
    dispatch(createUser(user))
  }

  return { create }
}

export default useUserActions


