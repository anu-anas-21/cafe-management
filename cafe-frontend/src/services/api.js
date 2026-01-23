import axios from "axios";

const API_URL = "https://localhost:8080/api/menu";

export const getMenu = () => axios.get(API_URL);
export const addMenu = (item) => axios.post(API_URL, item);