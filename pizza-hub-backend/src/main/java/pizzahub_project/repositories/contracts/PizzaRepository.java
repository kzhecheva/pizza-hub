package pizzahub_project.repositories.contracts;

import pizzahub_project.models.Pizza;

import java.util.List;

public interface PizzaRepository {

    List<Pizza> showAll();

    Pizza getById(int id);

    Pizza getByName(String pizzaName);
}
