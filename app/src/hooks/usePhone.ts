import { useEffect, useState } from 'react'
import { getPhone } from '@/services'
import { Phone } from '@/models'

const usePhone = ({ phoneId }: { phoneId: string }) => {
  const [phone, setPhone] = useState<Phone | null>(null)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    let isMounted = true

    const fetchPhone = async () => {
      try {
        setPhone(null);
        const phone = await getPhone({ phoneId });
        if (isMounted) {
          setPhone(phone)
        }
      } catch (error) {
        if (isMounted) {
          setError(`Error fetching phone`)
        }
      }
    }

    fetchPhone();

    return () => {
      isMounted = false
    }
  }, [phoneId])

  return { phone, error }
}

export default usePhone;
