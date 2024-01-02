import { useUserActions } from '@/hooks'
import { useEffect, useState } from 'react'
import { userDefaultState } from '@/redux/users/slice'
import type { User } from '@/models'
import { Input, Button, Popover, PopoverTrigger, PopoverContent } from '@nextui-org/react'
import { Spinner } from '@/components'
import { useNavigate } from 'react-router-dom'

function LoginPage() {
  const { login, isLoggedIn } = useUserActions()
  const [formData, setFormData] = useState(userDefaultState)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(false)
  const navigate = useNavigate()

  useEffect(() => {
    isLoggedIn() && navigate('/')
  }, [isLoggedIn, error])

  const handeChange = (event: any) => {
    const { name, value } = event.target
    setFormData((prevFormData) => ({ ...prevFormData, [name]: value }))
  }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault()

    if (formData.name != '' && formData.password != '') {
      setLoading(true)
      const user: User = {
        name: formData.name,
        password: formData.password,
        wishlist: []
      }

      const valid = await login(user)
      setError(!valid)
      setLoading(false)
    }
  }

  return (
    <main className='flex flex-col items-center justify-center h-screen' >
      <form className='flex flex-col flex-wrap md:flex-nowrap gap-4 w-[500px]' onSubmit={handleSubmit} >
        <Input type='text' label='User' name='name' onChange={handeChange} color={error ? 'danger' : 'default'} />
        <Input type='password' label='Password' name='password' onChange={handeChange} color={error ? 'danger' : 'default'} />
        <Popover>
          <PopoverTrigger>
            <Button className='self-start' type='submit'>{loading ? <Spinner /> : 'Login'}</Button>
          </PopoverTrigger>
          {
            error &&
            (<PopoverContent>
              <div className='px-1 py-2'>
                <div className='text-tiny text-red-600'>Invalid User</div>
              </div>
            </PopoverContent>)
          }
        </Popover>
      </form>
    </main >
  )
}

export default LoginPage
