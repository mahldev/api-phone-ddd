import { Button } from '@nextui-org/react'
import { formatSize } from '@/util'

type PhoneStoragePickerProps = {
  storage: number
  className: string
  onClick?: () => void
}

const PhoneStoragePicker = ({ storage, className, onClick }: PhoneStoragePickerProps) => {
  return <Button className={`${className} w-28`} onPress={onClick} size='lg'>{formatSize(storage)}</Button>
}

export default PhoneStoragePicker
