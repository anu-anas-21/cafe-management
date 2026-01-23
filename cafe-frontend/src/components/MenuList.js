function MenuList() {
    return (
        <div className="grid">
            {menubar.map(item => (
                <div key={item.id} className="card">
                    <h3>{item.name}</h3>
                    <p>{item.category}</p>
                    <span>{item.price}</span>
                </div>
            ))}
        </div>
    );
}

export default MenuList;