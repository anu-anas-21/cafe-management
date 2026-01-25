import React, { useState } from 'react';
import './AdminModal.css'; // Reusing some styles

const AdminLoginModal = ({ isOpen, onClose, onLogin }) => {
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    if (!isOpen) return null;

    const handleSubmit = (e) => {
        e.preventDefault();
        if (password === 'admin123') { // Hardcoded password for now
            onLogin();
            setPassword('');
            setError('');
        } else {
            setError('Incorrect password. Please try again.');
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Admin Login</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Enter admin password"
                            required
                        />
                    </div>
                    {error && <p className="error-message" style={{ color: 'red', fontSize: '0.8rem', marginBottom: '1rem' }}>{error}</p>}
                    <div className="modal-actions">
                        <button type="button" onClick={onClose} className="cancel-btn">Cancel</button>
                        <button type="submit" className="submit-btn">Login</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AdminLoginModal;
