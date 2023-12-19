/* eslint-disable react/prop-types */
export default function PaymentOptions({
  PAYMENT_METHOD,
  phonePrice,
  selectedPaymentMethod,
  handlePaymentMethod,
}) {
  return (
    <div className="info-card">
      <p className="subtitle">3. Choose payment option</p>
      <div className="wrapper-info-card">
        <div className="payment-options">
          {PAYMENT_METHOD.map((payment) => {
            return (
              <div
                key={payment}
                className={`payment ${
                  payment === selectedPaymentMethod ? "active" : ""
                }`}
                onClick={() => handlePaymentMethod(payment)}
              >
                <p>{payment}</p>
                <p className="price">
                  {`
                          ${
                            payment === PAYMENT_METHOD.oneTime
                              ? phonePrice
                              : (phonePrice / 12).toFixed(2)
                          }
                             €`}
                </p>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
