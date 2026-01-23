import axios from "axios";

const BASE_URL = "https://localhost:8080/api";

export const getMenu = () => axios.get(`${BASE_URL}/menu`);
export const addMenu = (item) => axios.post('${BASE_URL}/menu', item);
export const placeOrder = (order) => axios.post('${BASE_URL}/orders', order);
export const getOrders = () => axios.get('${BASE_URL}/orders');