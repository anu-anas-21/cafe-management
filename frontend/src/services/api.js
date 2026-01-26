import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";
const API = `${API_BASE_URL}/api/menu`;

export const getMenu = () => axios.get(API);
export const addMenu = (item) => axios.post(API, item);
export const updateMenu = (id, item) => axios.put(`${API}/${id}`, item);
export const deleteMenu = (id) => axios.delete(`${API}/${id}`);
