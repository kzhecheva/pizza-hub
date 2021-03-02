package pizzahub_project.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizzahub_project.models.Pizza;
import pizzahub_project.services.contracts.PizzaService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    @ResponseBody
    public String getAllPizzas() {
        List<Pizza> pizzas = pizzaService.showAll();
        StringBuilder resp = new StringBuilder("[");
        Pizza lastPizza = pizzas.get(pizzas.size() - 1);
        for (Pizza pizza : pizzas) {
            resp.append("{\"id\":\"" + pizza.getPizzaId() +
                    "\", \"name\":\"" + pizza.getName() +
                    "\", \"description\":\"" + pizza.getDescription() +
                    "\", \"picture\":\"" + pizza.getPicture() +
                    "\"}");
            if (pizza != lastPizza) {
                resp.append(",");
            }
        }
        resp.append("]");
        return resp.toString();
    }

}