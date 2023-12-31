import { useLocation } from "react-router-dom"
import { capitalizeWord } from '@/util'

type Route = {
  name: string
  path: string
}

const useRoutes = (): Route[] => {
  const location = useLocation()
  const [_, ...routesName] = location.pathname.split("/")

  return routesName.map(name => {
    return (
      {
        name: capitalizeWord(name),
        path: `/${name}`
      }
    )
  })
}

export default useRoutes

