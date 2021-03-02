package pizzahub_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzahub_project.models.Pizza;
import pizzahub_project.repositories.contracts.PizzaRepository;
import pizzahub_project.services.contracts.PizzaService;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {
    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> showAll() {
        return pizzaRepository.showAll();
    }

    @Override
    public Pizza getById(long id) {
        return null;
    }

}
