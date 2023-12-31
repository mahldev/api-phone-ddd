import { ShopIcon } from '@/assets/icons'

type InfoCardProps = {
  icon: ReturnType<typeof ShopIcon>
  topText: string
  bottomText: string
}

const InfoCard = ({ icon, topText, bottomText }: InfoCardProps) => {
  return (
    <div className='flex gap-2 items-center justify-center'>
      {icon}
      < div className='' >
        <p className='text-[#717171]'>{topText}</p>
        <p className='font-medium'>{bottomText}</p>
      </div >
    </div>
  )
}

export default InfoCard
