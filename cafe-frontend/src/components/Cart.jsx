import React, {useState} from 'react';
import {placeOrder} from '../services/api';

function Cart() {
    const [customerName, setCustomerName] = useState('');

    const submitOrder = () => {
        placeOrder({ customerName})
            .then(() => alert('Order placed successfully'))
    };

    return (
        <div className='container mt-4'>
            <h3>Place Order</h3>
            <input className='form-control' placeholder='Customer Name' onChange={e => setCustomerName(e.target.value)} />
            <button className='btn btn-primary mt-2' onClick={submitOrder}>Place Order</button>
        </div>
    );
}

export default Cart;