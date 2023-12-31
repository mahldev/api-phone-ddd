import { useAppSelector } from '@/hooks/store'
import useUserActions from '@/hooks/useUserActions'

function LoginPage() {
  const { create } = useUserActions()
  const user = useAppSelector(state => state.user)

  return (
    <main>
      <label>
        name
        <input type="text" placeholder={user.name} />
      </label>

      <label>
        passwd
        <input type="text" placeholder={user.password} />
      </label>

      <label>
        Wishlist
        <input type="text" placeholder={JSON.stringify(user.wishlist)} />
      </label>

      <label>
        is logged
        <input type="text" placeholder={JSON.stringify(user.isLoggedIn)} />
      </label>

      <button onClick={() => create({ name: 'text', password: '123', wishlist: [], isLoggedIn: false })}>
        Login
      </button>
    </main>
  )
}

export default LoginPage
