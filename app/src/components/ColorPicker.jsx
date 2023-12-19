/* eslint-disable react/prop-types */

export default function ColorPicker({ colors, selectedColor, handleColor }) {
  return (
    <div className="info-card">
      <p className="subtitle">1. Choose color</p>
      <div className="wrapper-info-card">
        <div className="colors">
          {colors.map((color) => {
            return (
              <div
                key={color}
                className={`color ${color === selectedColor ? "active" : ""}`}
                style={{ backgroundColor: color }}
                onClick={() => handleColor(color)}
              ></div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
