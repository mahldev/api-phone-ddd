import {
  Dropdown,
  Input,
  Link,
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  DropdownTrigger,
  DropdownMenu,
  Button,
  DropdownItem
} from '@nextui-org/react'
import {
  HeartIcon,
  Logo,
  SearchIcon,
  ShoppingCartIcon,
  UserIcon,
} from '../../assets/icons.tsx'
import useUserActions from '@/hooks/useUserActions.ts'
import { useEffect } from 'react'

const links = [
  {
    href: '/',
    displayName: 'Home',
  },
  {
    href: '#',
    displayName: 'About',
  },
  {
    href: '#',
    displayName: 'Contact Us',
  },
  {
    href: '#',
    displayName: 'Blog',
  },
]


const HeaderTop = () => {
  const { reset, isLoggedIn } = useUserActions()

  const handleLogout = () => {
    reset()
  }


  return (
    <Navbar
      as='header'
      isBlurred={false}
      position='sticky'
      className='py-1'
      maxWidth='xl'
      height='70px'
    >
      <NavbarBrand>
        <Link href='/'>
          <Logo />
        </Link>
      </NavbarBrand>
      <NavbarContent
        as='div'
        className='items-center w-3/4 px-4'
        justify='center'
      >
        <Input
          classNames={{
            input: 'text-small',
            inputWrapper: 'h-full rounded-lg py-3',
          }}
          placeholder='Search'
          size='lg'
          startContent={<SearchIcon />}
          type='search'
        />
      </NavbarContent>
      <NavbarContent className='flex gap-12' justify='center'>
        {links.map((link) => {
          const isHome = link.displayName === 'Home'

          return (
            <NavbarItem key={link.displayName} isActive={isHome}>
              <Link href={link.href} color='foreground'>
                <p className={isHome ? '' : 'text-[#b3b3b3]'}>
                  {link.displayName}
                </p>
              </Link>
            </NavbarItem>
          )
        })}
      </NavbarContent>
      <NavbarContent className='flex gap-5 ml-3' justify='center'>
        <HeartIcon />
        <ShoppingCartIcon />
        <Dropdown>
          <DropdownTrigger>
            <button className='p-0'><UserIcon /></button>
          </DropdownTrigger>
          {
            isLoggedIn() ? (
              <DropdownMenu aria-label='Link Actions'>
                <DropdownItem key='profile' showDivider>Profile</DropdownItem>
                <DropdownItem key='logout' onClick={handleLogout}>Log out</DropdownItem>
              </DropdownMenu>
            )
              : (
                <DropdownMenu aria-label='Link Actions'>
                  <DropdownItem href='/login' key='signin'>Sign In</DropdownItem>
                </DropdownMenu>
              )
          }
        </Dropdown>
      </NavbarContent>
    </Navbar >
  )
}

export default HeaderTop
