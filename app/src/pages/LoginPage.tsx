import { Input, Button } from '@nextui-org/react'
import useUserActions from '@/hooks/useUserActions'

function LoginPage() {
  const { create } = useUserActions()

  return (
    <main className='flex flex-col items-center justify-center h-screen'>
      <form className='flex flex-col flex-wrap md:flex-nowrap gap-4 w-[500px]' >
        <Input type='text' label='User' />
        <Input type='password' label='Password' />
        <Button className='self-start'>Login</Button>
      </form>
    </main>
  )
}

export default LoginPage
