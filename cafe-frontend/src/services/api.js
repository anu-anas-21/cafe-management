import axios from "axios";

const API = "http://localhost:8080/api/menu";

export const getMenu = () => axios.get(API);
// Spring Boot expects the item body directly
export const addMenu = (item) => axios.post(API, item);
export const updateMenu = (id, item) => axios.put(`${API}/${id}`, item);
export const deleteMenu = (id) => axios.delete(`${API}/${id}`);
