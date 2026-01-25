import React, { useState, useEffect } from 'react';
import { content as fallbackContent } from '../data/content';
import { addMenu } from '../services/api';
import AdminLoginModal from './AdminLoginModal'; // Import Login Modal
import './LandingPage.css';

// Admin Order View Component (Internal)
const AdminOrdersModal = ({ isOpen, onClose }) => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        if (isOpen) {
            fetch('http://localhost:8080/api/orders')
                .then(res => res.json())
                .then(setOrders)
                .catch(err => console.error("Error fetching orders:", err));
        }
    }, [isOpen]);

    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content admin-orders-content">
                <h2>Active Orders</h2>
                <div className="orders-list">
                    {(!orders || orders.length === 0) ? <p>No active orders.</p> : orders.map((order, idx) => (
                        <div key={idx} className="order-card">
                            <div className="order-header">
                                <span>Table: {order.tableNumber || "N/A"}</span>
                                <span>{order.timestamp ? new Date(order.timestamp).toLocaleTimeString() : ""}</span>
                                <span className={`status ${order.status}`}>{order.status}</span>
                            </div>
                            <ul className="order-items-list">
                                {(order.items || []).map((item, i) => (
                                    <li key={i}>{item.name} - {item.price} AED</li>
                                ))}
                            </ul>
                            <div className="order-total">Total: {order.total} AED</div>
                        </div>
                    ))}
                </div>
                <div className="modal-actions">
                    <button onClick={onClose} className="cancel-btn">Close</button>
                </div>
            </div>
        </div>
    );
};

const LandingPage = () => {
    const [activeTab, setActiveTab] = useState('');
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [cart, setCart] = useState([]);
    const [isAdminOrdersOpen, setIsAdminOrdersOpen] = useState(false);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isAdminLoginOpen, setIsAdminLoginOpen] = useState(false);

    const fetchData = () => {
        fetch('http://localhost:8080/api/menu')
            .then(res => res.json())
            .then(backendData => {
                setData({
                    ...fallbackContent,
                    menuItems: backendData.menuItems,
                    menuExperience: { ...fallbackContent.menuExperience, ...backendData.menuExperience }
                });
                const keys = Object.keys(backendData.menuItems);
                if (keys.length > 0 && !activeTab) setActiveTab(keys[0]);
                setLoading(false);
            })
            .catch(err => {
                console.error("Failed to fetch menu:", err);
                setData(fallbackContent);
                setLoading(false);
            });
    };

    useEffect(() => { fetchData(); }, []);

    const addToCart = (item) => {
        setCart([...cart, item]);
    };

    const placeOrder = async () => {
        if (cart.length === 0) return alert("Your cart is empty!");
        const tableNumber = prompt("Enter your Table Number:");
        if (!tableNumber) return;

        try {
            const response = await fetch('http://localhost:8080/api/orders', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ tableNumber, items: cart })
            });
            if (response.ok) {
                alert("Order placed successfully!");
                setCart([]);
            } else {
                alert("Failed to place order.");
            }
        } catch (error) {
            console.error("Error placing order:", error);
            alert("Error placing order.");
        }
    };

    const handleAdminAccess = () => {
        if (isAuthenticated) {
            setIsAdminOrdersOpen(true);
        } else {
            setIsAdminLoginOpen(true);
        }
    };

    if (loading) return <div className="loading">Loading Experience...</div>;
    if (!data) return null;

    return (
        <div className="landing-page">
            <header className="header">
                <div className="logo">Downtown Cafe</div>
                <nav>
                    {data.header.navigation.map((item, index) => (
                        <a key={index} href={item.href} target={item.external ? "_blank" : "_self"} rel={item.external ? "noopener noreferrer" : ""}>
                            {item.label}
                        </a>
                    ))}
                    {/* Admin Access hidden in nav or triggered via secret/button */}
                </nav>
            </header>

            <section id="hero" className="hero">
                <div className="hero-content">
                    <h1>{data.hero.headline}</h1>
                    <p>{data.hero.subHeadline}</p>
                </div>
            </section>

            <section id="hotel-info" className="section hotel-info">
                <h2>{data.hotelInfo.title}</h2>
                <p>{data.hotelInfo.description}</p>
            </section>

            <section id="photos" className="section photos-section">
                <h2>Our Ambience</h2>
                <div className="photo-grid">
                    <img src="https://images.unsplash.com/photo-1554118811-1e0d58224f24?q=80&w=1000&auto=format&fit=crop" alt="Cafe Interior" />
                    <img src="https://images.unsplash.com/photo-1559925393-8be0ec4767c8?q=80&w=1000&auto=format&fit=crop" alt="Coffee Art" />
                    <img src="https://images.unsplash.com/photo-1509042239860-f550ce710b93?q=80&w=1000&auto=format&fit=crop" alt="Beans" />
                    <img src="https://images.unsplash.com/photo-1497935586351-b67a49e012bf?q=80&w=1000&auto=format&fit=crop" alt="Social" />
                </div>
            </section>

            <section id="menu" className="section menu-experience">
                <h2>Menu Experience</h2>
                <div className="menu-intros">
                    {data.menuExperience && (
                        <>
                            <div className="intro-card"><h3>Signature Blends</h3><p>{data.menuExperience.signatureBlends}</p></div>
                            <div className="intro-card"><h3>Chef’s Specials</h3><p>{data.menuExperience.chefsSpecials}</p></div>
                            <div className="intro-card"><h3>Artisan Pastries</h3><p>{data.menuExperience.artisanPastries}</p></div>
                        </>
                    )}
                </div>
            </section>

            <section className="section menu-items">
                <div className="menu-tabs">
                    {Object.keys(data.menuItems).map(category => (
                        <button key={category} onClick={() => setActiveTab(category)} className={activeTab === category ? 'active' : ''}>
                            {category}
                        </button>
                    ))}
                </div>

                <div className="menu-list-container">
                    {data.menuItems && data.menuItems[activeTab] && (
                        <div className="menu-grouped-grid">
                            {Object.entries(data.menuItems[activeTab].reduce((acc, item) => {
                                const cat = item.subCategory || "General";
                                if (!acc[cat]) acc[cat] = [];
                                acc[cat].push(item);
                                return acc;
                            }, {})).map(([subCat, items]) => (
                                <div key={subCat} className="menu-subcategory-group">
                                    <h3 className="subcategory-title">{subCat}</h3>
                                    <div className="subcategory-items">
                                        {items.map((item) => (
                                            <div key={item.id} className="menu-item-compact">
                                                <div className="item-row">
                                                    <span className="item-name">{item.name}</span>
                                                    <span className="item-price">{item.price} AED</span>
                                                </div>
                                                {item.description && <p className="item-desc">{item.description}</p>}
                                                <button className="add-to-order-btn" onClick={() => addToCart(item)}>+ Add to Order</button>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </div>

                <div className="menu-actions">
                    <button className="secondary-button" onClick={() => window.open("https://drive.google.com/file/d/1K2k1iDkkdEFgN0rQO80NhopdLpPmetx6/view", "_blank")}>
                        {data.interface.viewFullMenu}
                    </button>
                    <button className="admin-button" onClick={handleAdminAccess}>
                        Admin Orders
                    </button>
                </div>
            </section>

            {/* Floating Cart */}
            {cart.length > 0 && (
                <div className="floating-cart">
                    <h3>Current Order ({cart.length})</h3>
                    <p>Total: {cart.reduce((sum, item) => sum + item.price, 0)} AED</p>
                    <button onClick={placeOrder} className="place-order-btn">Place Order</button>
                </div>
            )}

            <AdminOrdersModal isOpen={isAdminOrdersOpen} onClose={() => setIsAdminOrdersOpen(false)} />

            <AdminLoginModal
                isOpen={isAdminLoginOpen}
                onClose={() => setIsAdminLoginOpen(false)}
                onLogin={() => {
                    setIsAuthenticated(true);
                    setIsAdminLoginOpen(false);
                    setIsAdminOrdersOpen(true);
                }}
            />

            <footer id="footer" className="footer">
                <p>{data.footer.address}</p>
                <p>{data.footer.hours}</p>
                <p className="social">{data.footer.socialCta}</p>
            </footer>
        </div>
    );
};

export default LandingPage;
