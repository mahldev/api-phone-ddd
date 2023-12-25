import { useState, useEffect } from 'react'
import Header from '../components/Header'
import { getAllPhones } from '@/services/phoneService'
import { Phone } from '@/models/phone'
import { Spinner } from '@nextui-org/react'
import PhoneCard from '@/components/PhoneCard'

export function AllPhonePage() {
  const [loading, setLoading] = useState(true)
  const [phones, setPhones] = useState<Phone[]>()

  useEffect(() => {
    async function fetchPhones() {
      const allPhones = await getAllPhones()
      setPhones(allPhones)
      setLoading(false)
    }

    fetchPhones()
  }, [])

  return (
    <>
      <Header />
      <main className='flex items-center justify-center mt-2'>
        {loading ? (
          <Spinner />
        ) : (
          <section className='grid justify-center grid-cols-3 gap-4'>
            {phones?.map((phone) => {
              const { images, name, price } = phone
              return (
                <PhoneCard
                  key={phone.id}
                  image={images[0]}
                  name={name}
                  price={price}
                />
              )
            })}
          </section>
        )}
      </main>
    </>
  )
}
