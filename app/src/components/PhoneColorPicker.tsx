import { PhoneColor } from '@/models'
import { Tooltip } from '@nextui-org/react'
import { capitalizeWord } from '@/util'

interface PhoneColorPickerProps extends PhoneColor {
  onClick?: () => void
  className: string
}

enum Colors {
  White = 'white',
}

const PhoneColorPicker = ({ color, commercialName, className, onClick }: PhoneColorPickerProps) => {
  const isWhite = color === Colors.White
  return (
    <Tooltip className='rounded-md px-5 py-2' placement={'bottom'} content={capitalizeWord(commercialName)} >
      <div className={`flex rounded-full items-center justify-center w-10 h-10 ${className}`}>
        <span
          className={`${isWhite ? 'border-1 border-gray-600' : ''} rounded-full w-8 h-8 cursor-pointer`}
          style={{ backgroundColor: `${color}` }}
          onClick={onClick}
        />
      </div>
    </Tooltip>
  )
}

export default PhoneColorPicker
