import { useUserActions } from '@/hooks'
import {
  DropdownSection,
  DropdownItem,
  DropdownMenu,
  Dropdown,
  DropdownTrigger,
  Badge
} from "@nextui-org/react"

const ShoppinCartItems = () => {
  const {
    numberOfItemsOnCart
  } = useUserActions()

  return (
    <Dropdown>
      <DropdownTrigger>
        <button className='-mb-2'>
          <Badge content={numberOfItemsOnCart()} disableAnimation>
          </Badge>
        </button>
      </DropdownTrigger>
      <DropdownMenu>
        <DropdownSection>
          <DropdownItem isReadOnly>
          </DropdownItem>
          <DropdownItem as={'a'} href='/order' >
            Complete the order
          </DropdownItem>
        </DropdownSection>
      </DropdownMenu>
    </Dropdown>
  )
}

export default ShoppinCartItems
