/* eslint-disable react/prop-types */
export default function ShippingOptions({
  SHIPPING_OPTIONS,
  selectedShipping,
  handleShipping,
}) {
  return (
    <div className="shipping-options">
      {SHIPPING_OPTIONS.map((shipping) => {
        return (
          <div
            key={shipping.message}
            className={`shipping ${
              shipping === selectedShipping ? "active" : ""
            }`}
            onClick={() => handleShipping(shipping)}
          >
            {shipping.icon}
            <div>
              <p>{shipping.message}</p>
              <small>{shipping.description}</small>
            </div>
          </div>
        );
      })}
    </div>
  );
}
