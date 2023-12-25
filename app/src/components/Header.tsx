import { Link, Navbar, NavbarBrand } from '@nextui-org/react'
import {
  CamaraIcon,
  ComputerIcon,
  GamingIcon,
  HeadphonesIcon,
  HeartIcon,
  Logo,
  PhoneIcon,
  SearchIcon,
  ShoppingCartIcon,
  SmartwatchIcon,
  UserIcon,
} from '../../public/icons.tsx'

export default function Header() {
  const icons = [
    {
      icon: <PhoneIcon />,
      displayName: 'Phones',
      route: '/phones',
    },
    {
      icon: <ComputerIcon />,
      displayName: 'Computers',
      route: '',
    },
    {
      icon: <SmartwatchIcon />,
      displayName: 'Smart Watches',
      route: '',
    },
    {
      icon: <CamaraIcon />,
      displayName: 'Cameras',
      route: '',
    },
    {
      icon: <HeadphonesIcon />,
      displayName: 'Headphones',
      route: '',
    },
    {
      icon: <GamingIcon />,
      displayName: 'Gaming',
      route: '',
    },
  ]

  return (
    <header className='flex flex-col'>
      <section className='flex items-center justify-center gap-8 p-5'>
        <div>
          <Link href='/' color='foreground'>
            <Logo />
          </Link>
        </div>
        <div className='bg-[#f5f5f5] rounded-lg p-4 w-1/4'>
          <label className='flex gap-2'>
            <SearchIcon />
            <input
              className='bg-[#f5f5f5]'
              type='search'
              name='search'
              id='search'
              placeholder='Search'
            />
          </label>
        </div>
        <nav>
          <ul className='flex gap-12'>
            <li>
              <Link href='/' color='foreground'>
                Home
              </Link>
            </li>
            <li>About</li>
            <li>Contact Us</li>
            <li>Blog</li>
          </ul>
        </nav>
        <div className='flex gap-5'>
          <HeartIcon />
          <ShoppingCartIcon />
          <UserIcon />
        </div>
      </section>
      <section className='flex items-center justify-center bg-[#2E2E2E] w-full p-3'>
        {icons.map((icon, index) => {
          const isLast = index === icons.length - 1

          return (
            <Link
              key={icon.displayName}
              href={icon.route}
              color='foreground'
              className={`flex items-center gap-3 text-[#969696] text-center px-8 
              ${isLast ? '' : 'border-r-2 border-solid border-[#585858]'} `}
            >
              {icon.icon}
              {icon.displayName}
            </Link>
          )
        })}
      </section>
    </header>
  )
}
