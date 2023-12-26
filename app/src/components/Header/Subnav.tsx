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
    <Navbar as='section' className='flex bg-[#2E2E2E] w-full' maxWidth='xl'>
      <NavbarContent>
        {icons.map((icon, index) => {
          const isLast = index === icons.length - 1

          return (
            <NavbarItem key={icon.displayName}>
              <Link
                href={icon.route}
                color='foreground'
                className={`flex items-center gap-3 text-[#969696] text-center px-8 
              ${isLast ? '' : 'border-r-2 border-solid border-[#585858]'} `}
              >
                {icon.icon}
                {icon.displayName}
              </Link>
            </NavbarItem>
          )
        })}
      </NavbarContent>
    </Navbar>
  )
}

export default Subnav
