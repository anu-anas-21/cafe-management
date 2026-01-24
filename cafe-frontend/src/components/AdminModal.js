import React, { useState } from 'react';
import './AdminModal.css';

const AdminModal = ({ isOpen, onClose, onAdd }) => {
    const [formData, setFormData] = useState({
        name: '',
        price: '',
        category: 'Coffee & Tea', // Default
        subCategory: 'Hot Coffee', // Default
        description: ''
    });

    if (!isOpen) return null;

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onAdd(formData);
        setFormData({ name: '', price: '', category: 'Coffee & Tea', subCategory: 'Hot Coffee', description: '' }); // Reset
        onClose();
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Add New Menu Item</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Item Name</label>
                        <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                    </div>

                    <div className="form-group row">
                        <div className="half">
                            <label>Price (AED)</label>
                            <input type="number" name="price" value={formData.price} onChange={handleChange} required />
                        </div>
                        <div className="half">
                            <label>Category</label>
                            <select name="category" value={formData.category} onChange={handleChange}>
                                <option value="Coffee & Tea">Coffee & Tea</option>
                                <option value="Cold Beverages">Cold Beverages</option>
                                <option value="Breakfast">Breakfast</option>
                                <option value="Soups, Salads & Apps">Soups, Salads & Apps</option>
                                <option value="Main Courses">Main Courses</option>
                                <option value="Desserts & Shisha">Desserts & Shisha</option>
                            </select>
                        </div>
                    </div>

                    <div className="form-group">
                        <label>Sub-Category (Group)</label>
                        <input type="text" name="subCategory" value={formData.subCategory} onChange={handleChange} list="subCatOptions" placeholder="e.g., Hot Coffee, Entrees" required />
                        <datalist id="subCatOptions">
                            <option value="Hot Coffee" />
                            <option value="Iced Coffee" />
                            <option value="Black Coffee" />
                            <option value="Fresh Juices" />
                            <option value="Mocktails" />
                            <option value="Entrees" />
                            <option value="Sandwiches" />
                        </datalist>
                    </div>

                    <div className="form-group">
                        <label>Description (Optional)</label>
                        <textarea name="description" value={formData.description} onChange={handleChange} rows="3"></textarea>
                    </div>

                    <div className="modal-actions">
                        <button type="button" onClick={onClose} className="cancel-btn">Cancel</button>
                        <button type="submit" className="submit-btn">Add Item</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AdminModal;
