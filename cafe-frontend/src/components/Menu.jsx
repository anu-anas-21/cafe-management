import React, {use, useEffect, useState} from 'react';
import {getMenu} from '../services/api';

function Menu() {
    const [menu, setMenu] = useState([]);

    useEffect(() => {
        getMenu().then(res => setMenu(res.data));
    }, []);

    return (
        <div>
            <div className='container mt-4'>
                <h2>Downtown Café Menu</h2>
                <ul className='list-group'>
                    {menu.map(item => (
                        <li key={item.id} className='list-group-item'>
                            {item.name} - AED {item.price}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default Menu;