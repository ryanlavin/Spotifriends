import logo from './logo.svg';
import './App.css';
import Dashboard from './components/dashboard';
import Login from './components/login';
import Matching from './components/matching';
import { Route } from 'react-router';
import Profile from './components/profile';
import NavBar from './components/NavBar';

function App() {
  return (
    <div className="App">
      <NavBar/>
      <Route exact path="/" component={Dashboard} />
      <Route exact path="/dashboard" component={Dashboard} />
      <Route exact path="/login" component={Login} />
      <Route exact path="/matching" component={Matching} />
      <Route exact path="/profile" component={Profile} />
    </div>
  );
}

export default App;
