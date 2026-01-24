import React, { useState } from 'react';
import { content } from '../data/content';
import './LandingPage.css';

const LandingPage = () => {
    const [activeTab, setActiveTab] = useState('breakfast');

    return (
        <div className="landing-page">
            {/* Header */}
            <header className="header">
                <div className="logo">Downtown Cafe</div>
                <nav>
                    {content.header.navigation.map((item, index) => (
                        <a key={index} href={item.href}>{item.label}</a>
                    ))}
                </nav>
            </header>

            {/* Hero Section */}
            <section id="hero" className="hero">
                <div className="hero-content">
                    <h1>{content.hero.headline}</h1>
                    <p>{content.hero.subHeadline}</p>
                    <button className="cta-button">Book a Table</button>
                </div>
            </section>

            {/* Hotel Info */}
            <section id="hotel-info" className="section hotel-info">
                <h2>{content.hotelInfo.title}</h2>
                <p>{content.hotelInfo.description}</p>
            </section>

            {/* Menu Experience */}
            <section id="menu" className="section menu-experience">
                <h2>Menu Experience</h2>
                <div className="menu-intros">
                    <div className="intro-card">
                        <h3>Signature Blends</h3>
                        <p>{content.menuExperience.signatureBlends}</p>
                    </div>
                    <div className="intro-card">
                        <h3>Chef’s Specials</h3>
                        <p>{content.menuExperience.chefsSpecials}</p>
                    </div>
                    <div className="intro-card">
                        <h3>Artisan Pastries</h3>
                        <p>{content.menuExperience.artisanPastries}</p>
                    </div>
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
                    {content.menuItems[activeTab].map((item) => (
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
                    <button className="secondary-button">{content.interface.viewFullMenu}</button>
                    {/* Admin view simulation */}
                    <button className="admin-button">{content.interface.adminAddItem}</button>
                </div>
            </section>

            {/* Footer */}
            <footer id="footer" className="footer">
                <p>{content.footer.address}</p>
                <p>{content.footer.hours}</p>
                <p className="social">{content.footer.socialCta}</p>
            </footer>
        </div>
    );
};

export default LandingPage;
