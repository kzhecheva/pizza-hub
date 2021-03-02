package pizzahub_project.services.contracts;

import pizzahub_project.models.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> showAll();

    Pizza getById(long id);

}
