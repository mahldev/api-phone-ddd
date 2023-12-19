import "../css/PhoneRoute.css";

import { useParams } from "react-router-dom";
import Header from "../components/Header";

import { useState, useEffect } from "react";
import ColorPicker from "../components/ColorPicker";
import StoragePicker from "../components/StoragePicker";
import PaymentOptions from "../components/PaymentOptions";
import ShippingOptions from "../components/ShippingOptions";

const PAYMENT_METHOD = {
  oneTime: "One-Time payment",
  fragmented: "12 months",
};

const SHIPPING_OPTIONS = {
  standard: {
    message: "Standard | 2-4 days",
    description: "Our courier will deliver to the specified address",
    icon: (
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
        <path d="M7 17m-2 0a2 2 0 1 0 4 0a2 2 0 1 0 -4 0" />
        <path d="M17 17m-2 0a2 2 0 1 0 4 0a2 2 0 1 0 -4 0" />
        <path d="M5 17h-2v-11a1 1 0 0 1 1 -1h9v12m-4 0h6m4 0h2v-6h-8m0 -5h5l3 5" />
      </svg>
    ),
  },
  store: {
    message: "Pick up from the Store",
    description: "To pick up today",
    icon: (
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
        <path d="M3 21l18 0" />
        <path d="M3 7v1a3 3 0 0 0 6 0v-1m0 1a3 3 0 0 0 6 0v-1m0 1a3 3 0 0 0 6 0v-1h-18l2 -4h14l2 4" />
        <path d="M5 21l0 -10.15" />
        <path d="M19 21l0 -10.15" />
        <path d="M9 21v-4a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v4" />
      </svg>
    ),
  },
};

export default function PhoneDetailsPage() {
  const { id } = useParams();
  const [phone, setPhone] = useState(null);
  const [selectedColor, setSelectedColor] = useState();
  const [selectedStorage, setSelectedStorage] = useState();
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState(
    PAYMENT_METHOD.oneTime
  );
  const [selectedShipping, setSelectedShipping] = useState(
    SHIPPING_OPTIONS.standard
  );

  const colorToImage = {};

  const handleColor = (color) => {
    setSelectedColor(color);
  };

  const handleStorage = (storage) => {
    setSelectedStorage(storage);
  };

  const handlePaymentMethod = (method) => {
    setSelectedPaymentMethod(method);
  };

  const handleShipping = (shipping) => {
    setSelectedShipping(shipping);
  };

  const habndleSubmit = (e) => {
    e.preventDefault();
    console.log("Selected color: ", selectedColor);
    console.log("Selected storage: ", selectedStorage);
    console.log("Selected payment method: ", selectedPaymentMethod);
    console.log("Selected shipping: ", selectedShipping);
  };

  useEffect(() => {
    fetch(`http://127.0.0.1:8080/rest/api/phones/${id}`)
      .then((res) => res.json())
      .then((data) => {
        setPhone(data);
        setSelectedStorage(data.stragesSizes[0]);
        setSelectedColor(data.colors[0]);
      });
  }, [id]);

  if (!phone) {
    return <p>Loading...</p>;
  }

  phone.images.forEach((image) => {
    const lastDash = image.lastIndexOf("-");
    const dot = image.lastIndexOf(".");
    const color = image.substring(lastDash + 1, dot);

    colorToImage[color] = image;
  });

  return (
    <>
      <Header />
      <main>
        <form onClick={(e) => habndleSubmit(e)}>
          <section className="images-container">
            <div className="main-image">
              <img draggable="false" src={`/${colorToImage[selectedColor]}`} />
            </div>
          </section>

          <section className="product-info">
            <div className="wrapper">
              <h1>{phone.name}</h1>
            </div>

            <ColorPicker
              colors={phone.colors}
              selectedColor={selectedColor}
              handleColor={handleColor}
            />

            <StoragePicker
              storages={phone.storagesSizes}
              selectedStorage={selectedStorage}
              handleStorage={handleStorage}
            />

            <PaymentOptions
              PAYMENT_METHOD={Object.values(PAYMENT_METHOD)}
              phonePrice={phone.price}
              selectedPaymentMethod={selectedPaymentMethod}
              handlePaymentMethod={handlePaymentMethod}
            />
          </section>

          <section className="add-to-cart-section">
            <div className="info-card">
              <p className="subtitle">4. Choose shipping option</p>
              <ShippingOptions
                SHIPPING_OPTIONS={Object.values(SHIPPING_OPTIONS)}
                selectedShipping={selectedShipping}
                handleShipping={handleShipping}
              />

              <div className="total-amount">
                <p>Total amount: </p>
                <p>
                  {selectedPaymentMethod === PAYMENT_METHOD.oneTime
                    ? phone.price
                    : (phone.price / 12).toFixed(2)}{" "}
                  €
                </p>
              </div>

              <button className="add-to-cart">
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
                  <path d="M4 19a2 2 0 1 0 4 0a2 2 0 0 0 -4 0" />
                  <path d="M12.5 17h-6.5v-14h-2" />
                  <path d="M6 5l14 1l-.86 6.017m-2.64 .983h-10.5" />
                  <path d="M16 19h6" />
                  <path d="M19 16v6" />
                </svg>
                Add to cart
              </button>
            </div>
          </section>
        </form>
      </main>
    </>
  );
}
