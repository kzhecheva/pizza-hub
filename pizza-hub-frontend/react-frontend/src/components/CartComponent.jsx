import React, { Component, Fragment } from 'react';
import OrderService from "../services/OrderService";
import { Link } from 'react-router-dom';
import '../App.css';

class CartComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            order: {
                orderId: '',
                date: '',
                checkedOut: '',
                orderPizzasList: [
                    {
                        pizza: {
                            pizzaId: '',
                            name: '',
                        },
                        pizzaAmount: ''
                    }
                ]
            },
        }
    }

    componentDidMount() {
        OrderService.getCurrentOrder()
            .then(response => {
                // console.log(JSON.stringify(response));
                this.setState({ order: response.data })
            })
    }

    handleSubmit(orderId) {
        // console.log('orderId: ' + orderId);
        OrderService.checkOut(orderId);
    }

    changeAmout(pizzaId, pizzaAmount) {
        OrderService.editPizza(pizzaId, pizzaAmount).then(() => this.componentDidMount());
    }

    deletePizza(pizzaId) {
        OrderService.deletePizza(pizzaId).then(() => this.componentDidMount());;
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Shopping cart</h2>
                <div className="row">
                    <table className="table">
                        <tbody>
                            <tr key={this.state.order.orderId}>
                                <th colSpan="1">Order details</th>
                                <th colSpan="2">Order # {this.state.order.orderId}</th>
                            </tr>

                            {this.state.order.orderPizzasList.length !== 0 ?
                                (this.state.order.orderPizzasList.map(pizzas =>
                                    <tr key={pizzas.pizza.pizzaId} style={{ verticalAlign: 'center' }}>
                                        <td colSpan="1">{pizzas.pizza.name}</td>
                                        <td>
                                            <div className="input-group">
                                                <button className="btn btn-outline-secondary" type="button" onClick={() => this.changeAmout(pizzas.pizza.pizzaId, Number(pizzas.pizzaAmount) - 1)}>-</button>
                                                <span style={{ marginLeft: '15px', marginRight: '15px', textAlign: 'center' }}> {pizzas.pizzaAmount} </span>
                                                <button className="btn btn-outline-secondary" type="button" onClick={() => this.changeAmout(pizzas.pizza.pizzaId, Number(pizzas.pizzaAmount) + 1)}>+</button>
                                            </div>
                                        </td>
                                        <td>
                                            <button onClick={(event) => this.deletePizza(pizzas.pizza.pizzaId)} className="btn btn-dark">Delete</button>
                                        </td>
                                    </tr>
                                ))
                                :
                                (<tr><td colSpan="3">You shopping cart is empty!</td></tr>)
                            }
                            <tr>
                                <td colSpan="3">
                                    {this.state.order.orderPizzasList.length === 0 ?
                                        (<button className="btn btn-dark" disabled>Order</button>)
                                        :
                                        (<Link to="/orders">
                                            <button onClick={(event) => this.handleSubmit(this.state.order.orderId)} className="btn btn-dark">Order</button>
                                        </Link>)
                                    }
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="3" style={{ border: 'none', paddingTop: '5rem' }}>
                                    <Link to="/orders">
                                        <button className="btn btn-dark">Orders history</button>
                                    </Link>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>


            </div>
        );
    }
}


export default CartComponent;
