import React, { useState } from "react";

function OrderPage({ menu }) {
  const [cart, setCart] = useState([]);

  const add = (item) => setCart([...cart, item]);

  const total = cart.reduce((s, i) => s + i.price, 0);

  return (
    <>
      <h2>🛒 Orders</h2>

      <div className="grid">
        {menu.map(item => (
          <div className="card" key={item.id}>
            <h3>{item.name}</h3>
            <strong>₹{item.price}</strong>
            <button onClick={() => add(item)}>Add</button>
          </div>
        ))}
      </div>

      <h3>Total: ₹{total}</h3>
    </>
  );
}

export default OrderPage;