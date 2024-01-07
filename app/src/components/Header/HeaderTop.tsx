import useUserActions from '@/hooks/useUserActions.ts'
import {
  Badge,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  DropdownTrigger,
  Input,
  Link,
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
} from '@nextui-org/react'
import {
  HeartIcon,
  Logo,
  SearchIcon,
  ShoppingCartIcon,
  UserIcon,
} from '../../assets/icons.tsx'

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
  const {
    reset,
    isLoggedIn,
    numberOfItemsOnWishlist,
    numberOfItemsOnCart
  } = useUserActions()

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
      <NavbarContent className={`flex ${isLoggedIn() ? 'gap-5' : 'gap-2'} ml-3`} justify='center'>
        {
          isLoggedIn() ? (
            <NavbarContent>
              <Badge content={numberOfItemsOnWishlist()}>
                <HeartIcon />
              </Badge>
              <Badge content={numberOfItemsOnCart()}>
                <Link href='/order'>
                  <ShoppingCartIcon />
                </Link>
              </Badge>
              <Dropdown>
                <DropdownTrigger>
                  <button><UserIcon /></button>
                </DropdownTrigger>
                <DropdownMenu aria-label='Link Actions'>
                  <DropdownItem key='profile' showDivider>Profile</DropdownItem>
                  <DropdownItem key='logout' onClick={handleLogout}>Log out</DropdownItem>
                </DropdownMenu>
              </Dropdown>
            </NavbarContent>
          )
            : (
              <>
                <Link
                  color='foreground'
                  className='rounded-md px-5 py-2 bg-transparent border-1 whitespace-nowrap border-black font-medium text-sm'
                  href='/signin'
                >
                  Sign In
                </Link>
                <Link
                  color='foreground'
                  className='rounded-md px-5 py-2 bg-black text-white border-1 whitespace-nowrap border-black font-medium text-sm'
                  href='/login'
                >
                  Log In
                </Link>
              </>
            )
        }
      </NavbarContent>
    </Navbar >
  )
}

export default HeaderTop
