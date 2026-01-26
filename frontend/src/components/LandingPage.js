import React, { useState, useEffect, useCallback } from 'react';
import { content as fallbackContent } from '../data/content';
import AdminLoginModal from './AdminLoginModal'; // Import Login Modal
import './LandingPage.css';
import interior from "../Photos/Interior.png";
import coffee from "../Photos/Coffee.png";
import beans from "../Photos/Beans.png";
import cake from "../Photos/Cake.png";

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
                    {(!orders || orders.length === 0) ? <p>No active orders.</p> : orders.map((order, idx) => {
                        const updateStatus = async (newStatus) => {
                            try {
                                const response = await fetch(`http://localhost:8080/api/orders/${order.id}/status`, {
                                    method: 'PUT',
                                    headers: { 'Content-Type': 'application/json' },
                                    body: JSON.stringify({ status: newStatus })
                                });
                                if (response.ok) {
                                    // Refresh orders list
                                    const updatedOrders = orders.map(o => o.id === order.id ? { ...o, status: newStatus } : o);
                                    setOrders(updatedOrders);
                                }
                            } catch (err) {
                                console.error("Error updating status:", err);
                            }
                        };

                        return (
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
                                {order.status === 'PENDING' && (
                                    <button
                                        onClick={() => updateStatus('SERVED')}
                                        className="serve-btn"
                                    >
                                        Mark as Served
                                    </button>
                                )}
                            </div>
                        );
                    })}
                </div>
                <div className="modal-actions">
                    <button onClick={onClose} className="cancel-btn">Close</button>
                </div>
            </div>
        </div>
    );
};

const LandingPage = () => {
    const [activeTab, setActiveTab] = useState(null);
    const [showMenu, setShowMenu] = useState(false);
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [cart, setCart] = useState([]);
    const [isAdminOrdersOpen, setIsAdminOrdersOpen] = useState(false);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isAdminLoginOpen, setIsAdminLoginOpen] = useState(false);

    const fetchData = useCallback(() => {
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
    }, [activeTab]);

    useEffect(() => { fetchData(); }, [fetchData]);

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
                    {/* Admin Access moved here */}
                    <button className="admin-nav-button" onClick={handleAdminAccess}>
                        Admin
                    </button>
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
                    <img src={interior} alt="Cafe Interior" />
                    <img src={coffee} alt="Coffee Art" />
                    <img src={beans} alt="Beans" />
                    <img src={cake} alt="Social" />
                </div>
            </section>

            <section id="menu" className="section menu-experience">
                <h2>COLLECTIONS</h2>
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
                        <button
                            key={category}
                            onClick={() => {
                                setActiveTab(category);
                                setShowMenu(true);
                            }}
                            className={activeTab === category ? 'active' : ''}
                        >
                            {category}
                        </button>
                    ))}
                </div>

                <div className="menu-list-container">
                    {(!activeTab || !showMenu) && (
                        <div className="menu-placeholder">
                            <p>Please select a category to view menu items</p>
                        </div>
                    )}
                    {showMenu && activeTab && data.menuItems && data.menuItems[activeTab] && (
                        <div className="menu-grouped-grid">
                            {Object.entries(
                                data.menuItems[activeTab].reduce((acc, item) => {
                                    const cat = item.subCategory || "General";
                                    if (!acc[cat]) acc[cat] = [];
                                    acc[cat].push(item);
                                    return acc;
                                }, {})
                            ).map(([subCat, items]) => (
                                <div key={subCat} className="menu-subcategory-group">
                                    <h3 className="subcategory-title">{subCat}</h3>
                                    <div className="subcategory-items">
                                        {items.map(item => (
                                            <div key={item.id} className="menu-item-compact">
                                                <div className="item-row">
                                                    <span className="item-name">{item.name}</span>
                                                    <span className="item-price">{item.price} AED</span>
                                                </div>
                                                {item.description && (
                                                    <p className="item-desc">{item.description}</p>
                                                )}
                                                <button
                                                    className="add-to-order-btn"
                                                    onClick={() => addToCart(item)}
                                                >
                                                    + Add to Order
                                                </button>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
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
