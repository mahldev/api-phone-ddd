import { Image } from '@nextui-org/react'
import { Spinner } from '@/components'

type PhoneImageProps = {
  src: string | undefined,
  width?: number,
  height?: number,
  onClick?: () => void
  className?: string
}

const PATH = '/images'

const PhoneImage = ({ src, width, height, onClick, className }: PhoneImageProps) => {
  const isLoading = src === undefined
  return isLoading ? (
    <Spinner />
  ) : (
    <Image
      src={`${PATH}/${src}`}
      width={width}
      height={height}
      onClick={onClick}
      loading='lazy'
      className={className}
    />
  )
}

export default PhoneImage
