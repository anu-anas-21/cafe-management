import { deleteMenu } from "../services/api";

function MenuList({ menu, refresh }) {
  return (
    <div className="grid">
      {menu.map(item => (
        <div className="card" key={item.id}>
          <h3>{item.name}</h3>
          <p>{item.category}</p>
          <strong>₹{item.price}</strong>

          <button
            className="delete"
            onClick={() => {
              deleteMenu(item.id);
              refresh();
            }}>
            ❌ Delete
          </button>
        </div>
      ))}
    </div>
  );
}

export default MenuList;
