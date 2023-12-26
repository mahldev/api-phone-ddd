import useUserActions  from '@/hooks/useUserActions'

export default function LoginPage() {
  const { create } = useUserActions()

  return (
    <main>
      <label>
        name
        <input type="text" />
      </label>

      <label>
        passwd
        <input type="text" />
      </label>

      <button onClick={() => create({name: 'text', password: '123'})}>
        Login
      </button>
    </main>
  )
}
