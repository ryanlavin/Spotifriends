import React from "react";

import FormInput from "./input-component";
import '../css/login.css';

function onClick() {
    return null;
}

class Login extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            username: '',
            password: ''
        };
    }

    handleChange = event => {
        this.setState({
            username : event.target.value,
            password : event.target.value
        });
    };

    render() {
        return (
            <div className='sign-in'>
                <h2>Login-In Form</h2>
                <React.Fragment>
                    <input
                        type="text"
                        name="username"
                        value={this.state.username}
                    />
                    <input
                        type="text"
                        name="password"
                        value={this.state.password}
                        />
                    <button
                        onPress = {this.handleChange}
                        />
                </React.Fragment>
            </div>
        );
    }
}

export default Login;

