import axios from 'axios';

const ORDER_API_BASE_URL = "http://localhost:8080/api/orders";

class OrderService {

    getOrders() {
        return axios.get(ORDER_API_BASE_URL);
    }

    getCurrentOrder() {
        return axios.get(ORDER_API_BASE_URL + "/current-order");
    }

    addPizza(id) {
        return axios.post(ORDER_API_BASE_URL + "/add-pizza", id, { headers: { "Content-Type": "text/plain" } });
    }

    editPizza(id, amount) {
        return axios.put(ORDER_API_BASE_URL + "/edit-pizza", `${id}#${amount}`, { headers: { "Content-Type": "text/plain" } });
    }

    deletePizza(id) {
        return axios.post(ORDER_API_BASE_URL + "/delete-pizza", id, { headers: { "Content-Type": "text/plain" } });
    }

    checkOut(orderId) {
        return axios.post(ORDER_API_BASE_URL + "/check-out", orderId, { headers: { "Content-Type": "text/plain" } });
    }

}

export default new OrderService();