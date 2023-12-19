/* eslint-disable react/prop-types */

export default function StoragePicker({
  storages,
  selectedStorage,
  handleStorage,
}) {
  return (
    <div className="info-card">
      <p className="subtitle">2. Choose storage</p>
      <div className="wrapper-info-card">
        <div className="storages">
          {storages.map((storage) => {
            return (
              <div
                key={storage}
                className={`storage ${
                  storage === selectedStorage ? "active" : ""
                }`}
                onClick={() => handleStorage(storage)}
              >
                {storage}
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
