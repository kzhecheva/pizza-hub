import React, { Component } from 'react';
import '../App.css';

class FooterComponent extends Component {
    render() {
        return (
            <div>
                <div className="phantom" />
                <footer className="bg-dark justify-content-between">
                    <span className="text-muted">All Rights Reserved 2021 @PizzaHub</span>
                </footer>
            </div>
        );
    }
}

export default FooterComponent;
