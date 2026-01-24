import React, { useEffect, useState } from "react";
import { getMenu } from "./services/api";
import MenuForm from "./components/MenuForm";
import MenuList from "./components/MenuList";
import OrderPage from "./components/OrderPage";
import LandingPage from "./components/LandingPage";

import "./App.css";

function App() {
  const [menu, setMenu] = useState([]);

  // Mock loading for now as we focus on the Landing Page content
  const loadMenu = async () => {
    // const res = await getMenu();
    // setMenu(res.data);
    console.log("Menu loading skipped for content showcase");
  };

  useEffect(() => {
    // loadMenu();
  }, []);

  return (
    <div className="container">
      {/* 
        Temporarily rendering LandingPage to showcase generated content.
        Original components are commented out below.
      */}
      <LandingPage />

      {/* 
      <h1>☕ Downtown Cafe</h1>
      <MenuForm refresh={loadMenu} />
      <MenuForm refresh={loadMenu} />
      <MenuList menu={menu} refresh={loadMenu} />
      <OrderPage menu={menu} /> 
      */}
    </div>
  );
}

export default App;