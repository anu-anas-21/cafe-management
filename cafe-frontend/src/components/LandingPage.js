import React, { useState, useEffect } from 'react';
import { content as fallbackContent } from '../data/content';
import { addMenu } from '../services/api';
import AdminModal from './AdminModal'; // Import is crucial
import './LandingPage.css';

const LandingPage = () => {
    const [activeTab, setActiveTab] = useState('');
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [isModalOpen, setIsModalOpen] = useState(false); // Modal state

    const fetchData = () => {
        fetch('http://localhost:8080/api/menu')
            .then(res => res.json())
            .then(backendData => {
                setData({
                    ...fallbackContent,
                    menuItems: backendData.menuItems,
                    menuExperience: {
                        ...fallbackContent.menuExperience,
                        ...backendData.menuExperience
                    }
                });
                const keys = Object.keys(backendData.menuItems);
                if (keys.length > 0 && !activeTab) {
                    setActiveTab(keys[0]);
                }
                setLoading(false);
            })
            .catch(err => {
                console.error("Failed to fetch menu:", err);
                setData(fallbackContent);
                setLoading(false);
            });
    };

    useEffect(() => {
        fetchData();
    }, []);

    const handleAddItem = async (newItem) => {
        try {
            await addMenu(newItem);
            alert("Item added successfully!");
            fetchData(); // Refresh menu
        } catch (error) {
            console.error("Error adding item:", error);
            alert("Failed to add item. Check console.");
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
                    {/* Admin Button Triggers Modal */}
                    <button className="admin-button" onClick={() => setIsModalOpen(true)}>
                        {data.interface.adminAddItem || "Admin Add Item"}
                    </button>
                </div>
            </section>

            {/* Admin Modal Component */}
            <AdminModal
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onAdd={handleAddItem}
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
