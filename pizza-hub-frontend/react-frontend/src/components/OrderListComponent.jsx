import React, { Component } from 'react';
import OrderService from '../services/OrderService';
import Table from 'react-bootstrap/Table';
import '../App.css';

class OrderListComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [
                {
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
                }
            ],
        }
    }

    componentDidMount() {
        this.getOrders();
        this.interval = setInterval(() => this.getOrders(), 1000);
    }
    componentWillUnmount() {
        clearInterval(this.interval);
    }


    getOrders() {
        OrderService.getOrders()
            .then(response => {
                // console.log(JSON.stringify(response));
                this.setState({ orders: response.data })
            })
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Orders history</h2>
                <div>
                    {
                        this.state.orders.map(
                            orders =>
                                <Table>
                                    <thead>
                                        <tr key={orders.orderId + Math.random(1, 5)}>
                                            <th className="thWidth">Order number</th>
                                            <th className="thWidth">Order date</th>
                                            <th className="thWidth">Item list</th>
                                            <th className="thWidth">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr key={Math.random(1, 5)}>
                                            <td>{orders.orderId}</td>
                                            <td>{orders.date}</td>
                                            {orders.orderPizzasList.map(pizzas =>
                                                <tr key={pizzas.pizza.pizzaId + Math.random(1, 5)}>
                                                    <td>{pizzas.pizza.name}</td>
                                                    <td>{pizzas.pizzaAmount}</td>
                                                </tr>
                                            )}
                                            <td>finished</td>
                                        </tr>
                                    </tbody>
                                </Table>
                        )
                    }
                </div>
            </div >
        );
    }
}

export default OrderListComponent;
