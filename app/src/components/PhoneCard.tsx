import { Card, CardBody, Image } from '@nextui-org/react'

interface PhoneCardProps {
  // brandId,
  // colors,
  // id,
  image: string
  name: string
  price: number
  // storagesSizes,
}

const PhoneCard: React.FC<PhoneCardProps> = ({ image, name, price }) => {
  return (
    <Card className='rounded-md'>
      <CardBody className='flex flex-col items-center gap-1'>
        <Image
          radius='lg'
          width='100%'
          src={`../../public/${image}`}
          className='w-full object-cover h-[200px]'
        />
        <p>{name}</p>
        <p className='text-2xl '>
          {price}
          <p className='inline text-sm'>€</p>
        </p>
      </CardBody>
    </Card>
  )
}

export default PhoneCard
