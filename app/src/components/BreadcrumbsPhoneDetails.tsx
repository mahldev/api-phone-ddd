import { Breadcrumbs, BreadcrumbItem, Skeleton } from "@nextui-org/react"
import { useRoutes } from '@/hooks'

type BreadcrumbsPhoneDetailsProps = {
  phoneName: string | undefined,
}

const BreadcrumbsPhoneDetails = ({ phoneName }: BreadcrumbsPhoneDetailsProps) => {
  const routes = useRoutes()
  const isLoading = phoneName === undefined

  return isLoading ? (
    <div className="flex gap-2">
      {routes.map(route => <Skeleton key={route.name} className="h-3 w-10 rounded-lg" />)}
    </div>
  ) : (
    <Breadcrumbs>
      {routes.map((route, index) => {
        const lastIndex = index === routes.length - 1
        return (
          <BreadcrumbItem
            size='lg'
            key={route.path}
            href={route.path}
          >
            {lastIndex ? phoneName : route.name}
          </BreadcrumbItem>
        )
      })}
    </Breadcrumbs>
  )
}

export default BreadcrumbsPhoneDetails 
