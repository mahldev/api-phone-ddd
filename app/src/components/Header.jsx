import "../css/Header.css";

export default function Header() {
  return (
    <header className="header">
      <section className="section-1">
        <div className="logo">
          <a href="/">
            <svg
              width="32"
              height="32"
              viewBox="0 0 24 24"
              strokeWidth="2"
              stroke="currentColor"
              fill="none"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path stroke="none" d="M0 0h24v24H0z" fill="none" />
              <path d="M20 19l-8   -5.5l-8 5.5" />
              <path d="M12 4v9.5" />
              <path d="M12 4m-1 0a1 1 0 1 0 2  0a1 1 0 1 0   -2    0" />
              <path d="M4 19m-1 0a1 1 0 1 0 2 0a1 1 0 1 0 -2   0" />
              <path d="M20 19m-1 0a1 1 0 1 0 2 0a1 1 0 1 0 -2 0" />
            </svg>
            <p>E-Shop</p>
          </a>
        </div>

        <div className="search-container">
          <form action="">
            <input type="search" placeholder="Search for products" />

            <div className="wrapper">
              <select name="category">
                <option value="all">Choose brand</option>
              </select>
              <button className="icon icon-search">
                <svg
                  width="18"
                  height="18"
                  viewBox="0 0 24 24"
                  strokeWidth="2.5"
                  stroke="currentColor"
                  fill="none"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                >
                  <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                  <path d="M10 10m-7 0a7 7 0 1 0 14 0a7 7 0 1 0 -14 0" />
                  <path d="M21 21l-6 -6" />
                </svg>
              </button>
            </div>
          </form>
        </div>

        <div className="profile-container-header">
          <form id="login" hidden action="/login/"></form>
          <button form="login" className="icon login">
            Log in/Sign up
          </button>

          <div className="profile-container">
            <button className="icon">
              <svg
                width="24"
                height="24"
                viewBox="0 0 24 24"
                strokeWidth="2"
                stroke="currentColor"
                fill="none"
                strokeLinecap="round"
                strokeLinejoin="round"
              >
                <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                <path d="M8 7a4 4 0 1 0 8 0a4 4 0 0 0 -8 0" />
                <path d="M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
              </svg>
            </button>
            <button className="icon">
              <svg
                width="24"
                height="24"
                viewBox="0 0 24 24"
                strokeWidth="2"
                stroke="currentColor"
                fill="none"
                strokeLinecap="round"
                strokeLinejoin="round"
              >
                <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                <path d="M6 19m-2 0a2 2 0 1 0 4 0a2 2 0 1 0 -4 0" />
                <path d="M17 19m-2 0a2 2 0 1 0 4 0a2 2 0 1 0 -4 0" />
                <path d="M17 17h-11v-14h-2" />
                <path d="M6 5l14 1l-1 7h-13" />
              </svg>
            </button>
          </div>
        </div>
      </section>

      <section className="section-2">
        <ul className="ul-header">
          <li>
            <a className="link" href="/">
              Home
            </a>
          </li>
          <li>
            <a className="link" href="#">
              Promotions
            </a>
          </li>
          <li>
            <a className="link" href="#">
              Outlet
            </a>
          </li>
          <li>
            <a className="link sales" href="#">
              Spring Sale
            </a>
          </li>
        </ul>
      </section>
    </header>
  );
}
