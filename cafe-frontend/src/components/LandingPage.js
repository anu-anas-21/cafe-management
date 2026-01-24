import React, { useState, useEffect } from 'react';
import { content as fallbackContent } from '../data/content';
import './LandingPage.css';

const LandingPage = () => {
    const [activeTab, setActiveTab] = useState('breakfast');
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // Fetch data from Spring Boot backend
        fetch('http://localhost:8080/api/menu')
            .then(res => res.json())
            .then(backendData => {
                // Merge backend menu items with frontend static text
                setData({
                    ...fallbackContent,
                    menuItems: backendData.menuItems,
                    menuExperience: {
                        ...fallbackContent.menuExperience,
                        ...backendData.menuExperience
                    }
                });
                setLoading(false);
            })
            .catch(err => {
                console.error("Failed to fetch menu from backend, specific error:", err);
                // Fallback to static content if backend fails
                setData(fallbackContent);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div className="loading">Loading Experience...</div>;
    }

    // Ensure data is not null before rendering
    if (!data) return null;

    return (
        <div className="landing-page">
            {/* Header */}
            <header className="header">
                <div className="logo">Downtown Cafe</div>
                <nav>
                    {data.header.navigation.map((item, index) => (
                        <a key={index} href={item.href}>{item.label}</a>
                    ))}
                </nav>
            </header>

            {/* Hero Section */}
            <section id="hero" className="hero">
                <div className="hero-content">
                    <h1>{data.hero.headline}</h1>
                    <p>{data.hero.subHeadline}</p>
                    <button className="cta-button">Book a Table</button>
                </div>
            </section>

            {/* Hotel Info */}
            <section id="hotel-info" className="section hotel-info">
                <h2>{data.hotelInfo.title}</h2>
                <p>{data.hotelInfo.description}</p>
            </section>

            {/* Menu Experience */}
            <section id="menu" className="section menu-experience">
                <h2>Menu Experience</h2>
                <div className="menu-intros">
                    {data.menuExperience && (
                        <>
                            <div className="intro-card">
                                <h3>Signature Blends</h3>
                                <p>{data.menuExperience.signatureBlends}</p>
                            </div>
                            <div className="intro-card">
                                <h3>Chef’s Specials</h3>
                                <p>{data.menuExperience.chefsSpecials}</p>
                            </div>
                            <div className="intro-card">
                                <h3>Artisan Pastries</h3>
                                <p>{data.menuExperience.artisanPastries}</p>
                            </div>
                        </>
                    )}
                </div>
            </section>

            {/* Menu Items Showcase */}
            <section className="section menu-items">
                <div className="menu-tabs">
                    <button onClick={() => setActiveTab('breakfast')} className={activeTab === 'breakfast' ? 'active' : ''}>Breakfast</button>
                    <button onClick={() => setActiveTab('lunch')} className={activeTab === 'lunch' ? 'active' : ''}>Lunch</button>
                    <button onClick={() => setActiveTab('coffee')} className={activeTab === 'coffee' ? 'active' : ''}>Coffee</button>
                </div>
                <div className="menu-list">
                    {data.menuItems && data.menuItems[activeTab] && data.menuItems[activeTab].map((item) => (
                        <div key={item.id} className="menu-item">
                            <div className="item-header">
                                <h4>{item.name}</h4>
                                <span className="price">{item.price} AED</span>
                            </div>
                            <p>{item.description}</p>
                        </div>
                    ))}
                </div>
                <div className="menu-actions">
                    <button className="secondary-button">{data.interface.viewFullMenu}</button>
                    {/* Admin view simulation */}
                    <button className="admin-button">{data.interface.adminAddItem}</button>
                </div>
            </section>

            {/* Footer */}
            <footer id="footer" className="footer">
                <p>{data.footer.address}</p>
                <p>{data.footer.hours}</p>
                <p className="social">{data.footer.socialCta}</p>
            </footer>
        </div>
    );
};

export default LandingPage;
