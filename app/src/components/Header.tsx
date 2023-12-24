import { Link } from 'react-router-dom'

export default function Header() {
  return (
    <header className='flex items-center justify-center mt-3'>
      <ul className='flex gap-3'>
        <li><Link to={'/'}>Home</Link></li>
        <li><Link to={'/phones'}>Phones</Link></li>
      </ul>  
    </header>
  ) 
}