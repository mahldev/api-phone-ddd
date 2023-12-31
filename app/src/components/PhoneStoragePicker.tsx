import { Button } from "@nextui-org/react"

type PhoneStoragePickerProps = {
  storage: number
  className: string
  onClick?: () => void
}

const formatSize = (storage: number): string => {
  return storage >= 1000 ? `${storage / 1000}TB` : `${storage}GB`
}

const PhoneStoragePicker = ({ storage, className, onClick }: PhoneStoragePickerProps) => {
  return <Button className={`${className} w-28`} onPress={onClick} size="lg">{formatSize(storage)}</Button>
}

export default PhoneStoragePicker
