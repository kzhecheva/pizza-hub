import axios from 'axios';

const PIZZA_API_BASE_URL = "http://localhost:8080/api/pizzas";

class PizzaService {

    getPizzas() {
        return axios.get(PIZZA_API_BASE_URL);
    }
}

export default new PizzaService();