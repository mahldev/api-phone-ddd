import { Link, Navbar, NavbarContent, NavbarItem } from '@nextui-org/react'
import {
  CamaraIcon,
  ComputerIcon,
  GamingIcon,
  HeadphonesIcon,
  PhoneIcon,
  SmartwatchIcon,
} from '../../assets/icons.tsx'

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

const Subnav = () => {
  return (
    <Navbar
      as='section'
      position='static'
      className='flex justify-center bg-[#2E2E2E] w-full h-14'
      maxWidth='xl'
    >
      <NavbarContent className='felx items-center w-full h-10 gap-0' justify='center'>
        {icons.map((icon, index) => {
          const isLast = index === icons.length - 1

          return (
            <NavbarItem
              className={`flex items-center flex-1 justify-center ${isLast ? '' : 'border-r-2 border-solid border-[#585858] '}`}
              key={icon.displayName} >
              <Link
                href={icon.route}
                color='foreground'
                className='flex items-center gap-3 text-[#969696] text-center'
              >
                {icon.icon}
                {icon.displayName}
              </Link>
            </NavbarItem>
          )
        })}
      </NavbarContent >
    </Navbar >
  )
}

export default Subnav
