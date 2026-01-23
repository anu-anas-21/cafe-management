import axios from "axios";

const API = "http://localhost:8080/api/menu";

export const getMenu = () => axios.get(API);
export const addMenu = (item) => axios.post(API, item);
export const updateMenu = (id, item) => axios.put(`${API}/${id}`, item);
export const deleteMenu = (id) => axios.delete(`${API}/${id}`);
