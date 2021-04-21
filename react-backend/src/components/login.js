import React from "react";

import FormInput from "./input-component";
//import '../css/login.css';


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


    handleSubmit = event => {
        event.preventDefault();
        this.setState({username: '', password: ''});
    };

    handleChange = event => {
        const {value, name} = event.target;

        this.setState({[name]: value});
    };


    render() {
        return (
            <div className='sign-in'>
                <h2>Login-In Form</h2>
                <React.Fragment>
		<form onSubmit={this.handleSubmit}>
                    <FormInput
                        type="text"
                        name="username"
                        value={this.state.username}
                        handleChange={this.handleChange}
                        label="username"
                        required
                    />
                    <FormInput
                        type="text"
                        name="password"
                        value={this.state.password}
                        handleChange={this.handleChange}
                        label="password"
                        required
                        />
                    <button
                        type="submit"> Sign In </button>
		</form>
                </React.Fragment>
            </div>
        );
    }
}

export default Login;

