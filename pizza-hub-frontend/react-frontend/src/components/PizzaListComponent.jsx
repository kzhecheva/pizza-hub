import React, { Component } from 'react';
import PizzaService from '../services/PizzaService';
import OrderService from '../services/OrderService';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import '../App.css';


class PizzaListComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pizzas: [],
        }
    }

    componentDidMount() {
        PizzaService.getPizzas()
            .then(response => {
                this.setState({ pizzas: response.data })
            })
    }

    handleSubmit(id) {
        // console.log('id: ' + id);
        OrderService.addPizza(id);
    }

    render() {
        return (
            <div style={{ minWidth: '300px' }}>
                <h2 className="text-center">Pizza list</h2>
                <div className='row justify-content-center'>
                    {
                        this.state.pizzas.map(pizzas =>
                            <Card onClick={(event) => this.handleSubmit(pizzas.id)} style={{ minWidth: '285px', width: '18rem', margin: '2em' }} key={pizzas.id}>
                                <Card.Img variant="top" src={`data:image/png;base64,${pizzas.picture}`} style={{ minWidth: '255px', width: 'auto', height: '190px', margin: '1rem' }} />
                                <Card.Body>
                                    <Card.Title>{pizzas.name}</Card.Title>
                                    <Card.Text style={{ height: '195px' }}>{pizzas.description}</Card.Text>
                                    <Button variant="danger" style={{ position: 'relative' }}>Add to cart</Button>
                                </Card.Body>
                            </Card>
                        )
                    }
                </div>
            </div>
        );
    }
}

export default PizzaListComponent;
