import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { FaShoppingCart } from 'react-icons/fa';
import '../App.css';

class HeaderComponent extends Component {

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-dark bg-dark justify-content-between">
                        <Link to="/">
                            <input value="Pizza Hub" disabled className="btn btn-dark" style={{ fontSize: '20px' }}></input>
                        </Link>
                        <div className="form-inline">
                            <Link to="/orders/current-order">
                                <button className="btn btn-light">Shopping cart <FaShoppingCart style={{ marginLeft: '7px' }} /></button>
                            </Link>
                        </div>
                    </nav>
                </header>
                <div className="phantom" />
            </div>
        );
    }
}

export default HeaderComponent;
