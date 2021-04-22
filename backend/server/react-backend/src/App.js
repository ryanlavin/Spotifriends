import logo from './logo.svg';
import React from 'react';
import './App.css';
import Dashboard from './components/dashboard';
import Login from './components/login';
import Matching from './components/matching';
import { Route } from 'react-router';
import Profile from './components/profile';
import NavBar from './components/NavBar';

class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            friends: [],
            private: false,
        };

        this.post = this.post.bind(this);
        this.get = this.get.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

        post(e) { // for POST HTTP requests
            e.preventDefault();
        }

        get(e) { // for GET HTTP requests
            e.preventDefault();
        }

        handleChange(changeObject) {
            this.setState(changeObject);
        }

    render() {
        return (
            <div id="Background" className="App">
                <NavBar/>
                <Route exact path="/" component={Dashboard}/>
                <Route exact path="/dashboard" component={Dashboard}/>
                <Route exact path="/login" component={Login}/>
                <Route exact path="/matching" component={Matching}/>
                <Route exact path="/profile" component={Profile}/>
            </div>
        );
    }
}

export default App;
