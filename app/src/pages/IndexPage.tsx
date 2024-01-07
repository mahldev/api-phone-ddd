import { Button, Image } from '@nextui-org/react'
import { Header } from '@/components'

export function IndexPage() {
  return (
    <>
      <Header />
      <main>
        <section className='flex items-end justify-around h-[600px] bg-[#211c24]'>
          <div className='flex flex-col self-center gap-4 text-left text-white'>
            <h3 className='font-extralight text-8xl'>
              IPhone 15 <b className='font-semibold'>Pro</b>
            </h3>
            <p className='text-[#909090] font-semibold'>
              Created to change everything for the better. For everyone
            </p>
            <Button className='self-start px-12 py-6 font-semibold text-white bg-transparent rounded-md border-1'>
              Shop now
            </Button>
          </div>
          <Image
            src='/images/index-content/iphone.webp'
            alt=''
            width={380}
            radius='none'
            draggable='false'
            disableSkeleton
          />
        </section>
      </main>
    </>
  )
}
