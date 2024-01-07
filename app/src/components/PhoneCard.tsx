import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Image,
  Link
} from '@nextui-org/react'
import { HeartIconPhoneCard } from '@/assets/icons'
import { useEffect, useState } from 'react'

type PhoneCardProps = {
  id: number
  image: string
  name: string
  price: number
  isLiked: boolean
  handleLike: () => void
}


const PhoneCard = ({ id, image, name, price, isLiked: isLikedProp, handleLike }: PhoneCardProps) => {
  const [isLiked, setIsLiked] = useState(isLikedProp);

  useEffect(() => {
    setIsLiked(isLikedProp);
  }, [isLikedProp])

  return (
    <Card className={`py-2 rounded-md shadow-none bg-main-gray w-64`}>
      <CardHeader className='flex justify-end pb-0'>
        <HeartIconPhoneCard isLiked={isLiked} onClick={handleLike} />
      </CardHeader>
      <CardBody className='flex flex-col items-center pt-2'>
        <Image
          radius='lg'
          width='100%'
          src={`/images/${image}`}
          className='w-full object-cover h-[170px]'
        />
      </CardBody>
      <CardFooter className='flex flex-col items-center justify-center gap-3 pt-0 0 bg'>
        <p className='text-sm font-medium text-center'>{name}</p>
        <p className='text-xl font-semibold'>{`${price.toFixed()} €`}</p>
        <Button
          href={`/phones/${id}`}
          as={Link}
          className='py-5 text-xs font-semibold text-white bg-black rounded-md px-14'
        >
          Buy Now
        </Button>
      </CardFooter>
    </Card>
  )
}

export default PhoneCard
