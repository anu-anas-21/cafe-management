import React, { useEffect, useState } from "react";
import { getMenu } from "./services/api";
import MenuForm from "./components/MenuForm";
import MenuList from "./components/MenuList";
import OrderPage from "./components/OrderPage";

import "./App.css";

function App() {
  const [menu, setMenu] = useState([]);

  const loadMenu = async () => {
    const res = await getMenu();
    setMenu(res.data);
  };

  useEffect(() => {
    loadMenu();
  }, []);

  return (
    <div className="container">
      <h1>☕ Downtown Cafe</h1>
      <MenuForm refresh={loadMenu} />
      <MenuForm refresh={loadMenu} />
      <MenuList menu={menu} refresh={loadMenu} />
      <OrderPage menu={menu} />
    </div>
  );
}

export default App;