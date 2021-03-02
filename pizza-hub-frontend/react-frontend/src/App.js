import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PizzaListComponent from './components/PizzaListComponent';
import OrderListComponent from './components/OrderListComponent';
import CartComponent from './components/CartComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div className="container">
          <Switch>
            <Route path='/' exact component={PizzaListComponent}></Route>
            <Route path='/pizzas' component={PizzaListComponent}></Route>
            <Route path='/orders/current-order' exact component={CartComponent}></Route>
            <Route path='/orders' exact component={OrderListComponent}></Route>
            <PizzaListComponent />
          </Switch>
        </div>
        <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
