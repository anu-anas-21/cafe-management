import { useState} from 'react';
import {addMenu} from '../services/api';

function Menu() {
    const [item, setItem] = useState({
        name: '',
        category: '',
        price: '',
        available: true
    });

    const submit = async (e) => {
        e.preventDefault();
        await addMenu(item);
        refresh();
        setItem({ name: '', category: '', price: '', available: true });
    };

    return (
        <form className='form' onSubmit={submit}>
            <h2>Add New Item</h2>
            <input placeholder='Item name' value={item.name} onChange={(e) => setItem({...item, name: e.target.value})} />
            <input placeholder='Category' value={item.category} onChange={e => setItem({...item, category: e.target.value})} />
            <input placeholder='Price' type='number' value={item.price} onChange={e => setItem({...item, price: e.target.value})} /> 
            <button>Add Item</button>
        </form>
    );
}

export default Menu;