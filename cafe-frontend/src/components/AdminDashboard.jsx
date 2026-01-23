import React, {use, useEffect, useState} from "react";
import { addMenu, getOrders }  from "../services/api";

function AdminDashboard() {
    const [orders, setOrders] = useState([]);
    const [item, setItem] = useState({ name: '', category: '', price: 0, available: true});

    useEffect(() => {
        getOrders().then(res => setOrders(res.data));
    }, []);

    const saveItem = () => {
        addMenu(item).then(() => alert('Menu item added'));
    };

    return (
        <div className="container mt-4">
            <h3>Admin Dashboard</h3>

            <h5>Add Menu Item</h5>
            <input className="form-control" placeholder="Name" onChange={e => setItem({...item, name: e.target.value})} />
            <input className="form-control mt-2" placeholder="Category" onChange={e => setItem({...item, category: e.target.value})} />
            <input className="form-control mt-2" placeholder="Price" type="number" onChange={e => setItem({...item, price: e.target.value})}/>
            <button className="btn btn-success mt-2" onClick={saveItem}>Add</button>

            <h5 className="mt-4">Orders</h5>
            <ul className="list-group">
                {orders.map(o => (
                    <li key={o.id} className="list-group-item">
                        {o.customerName} - {o.status}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default AdminDashboard;