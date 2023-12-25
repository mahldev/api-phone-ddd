import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Image,
} from '@nextui-org/react'
import { HeartIconPhoneCard } from '../../public/icons'

type PhoneCardProps = {
  image: string
  name: string
  price: number
}

const PhoneCard: React.FC<PhoneCardProps> = ({ image, name, price }) => {
  return (
    <Card className={`py-2 rounded-md shadow-none bg-main-gray w-64`}>
      <CardHeader className='flex justify-end pb-0'>
        <HeartIconPhoneCard />
      </CardHeader>
      <CardBody className='flex flex-col items-center pt-2'>
        <Image
          radius='lg'
          width='100%'
          src={`../../public/images/${image}`}
          className='w-full object-cover h-[170px]'
        />
      </CardBody>
      <CardFooter className='flex flex-col items-center justify-center gap-3 pt-0 0'>
        <p className='text-sm font-medium text-center'>{name}</p>
        <p className='text-xl font-semibold'>{`${price.toFixed()} €`}</p>
        <Button className='py-5 text-xs font-semibold text-white bg-black rounded-md px-14'>
          Buy Now
        </Button>
      </CardFooter>
    </Card>
  )
}

export default PhoneCard
