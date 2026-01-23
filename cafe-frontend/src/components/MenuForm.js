import React, { useState } from "react";
import { addMenu } from "../services/api";

function MenuForm({ refresh }) {   // ✅ RECEIVE refresh HERE

  const [item, setItem] = useState({
    name: "",
    category: "",
    price: ""
  });

  const submit = async (e) => {
    e.preventDefault();
    await addMenu(item);
    refresh();          // ✅ NOW IT EXISTS
    setItem({ name: "", category: "", price: "" });
  };

  return (
    <form onSubmit={submit}>
      <input
        placeholder="Name"
        value={item.name}
        onChange={e => setItem({ ...item, name: e.target.value })}
      />

      <input
        placeholder="Category"
        value={item.category}
        onChange={e => setItem({ ...item, category: e.target.value })}
      />

      <input
        type="number"
        placeholder="Price"
        value={item.price}
        onChange={e => setItem({ ...item, price: e.target.value })}
      />

      <button>Add Item</button>
    </form>
  );
}

export default MenuForm;
