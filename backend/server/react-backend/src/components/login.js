import React from "react";
import "../css/login.css";
import FormInput from "./input-component";
//import '../css/login.css';
import axios from 'axios';
import {Link} from "react-router-dom";
import {SpotifyAuth, Scopes} from 'react-spotify-auth';
import 'react-spotify-auth/dist/index.css';

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loginUsername: "",
            loginPassword: "",
            registerUsername: "",
            registerPassword: "",
            access_token:""
        };
    }

    handleLogin = (event) => {
        event.preventDefault();
        axios.post("https:localhost:8080/login-api",
            {
                "username": this.state.loginUsername,
                "password": this.state.loginPassword
            });
        this.setState({ loginUsername: "", loginPassword: "" });
    };

    handleChange = (event) => {
        const { value, name } = event.target;

        this.setState({ [name]: value });
    };

    handleRegister = (event) => {
        event.preventDefault();
        axios.post("https:localhost:8080/register-api",
        {
            "username": this.state.registerUsername,
            "password": this.state.registerPassword
        }
        );
        this.setState({registerUsername: "", registerPassword: ""});
    };
    handleAccessToken = (token) => {
        /* this.setState({
            access_token: token
        },()=>{
            console.log("ACCESS TOKEN:" + this.state.access_token);
        }); */
        window.accessToken=token;
    }
    render() {
        return (
            <div className="sign-in">
                <div className="login">
                    <h2 id="login-header">Login</h2>
                    <div id="login-form-div">
                        <React.Fragment>
                            <form id="login-form" onSubmit={this.handleLogin}>
                                <h2 id="username-header">Username</h2>
                                <FormInput
                                    id="loginUsername"
                                    type="text"
                                    name="loginUsername"
                                    value={this.state.loginUsername}
                                    handleChange={this.handleChange}
                                    required
                                />
                                <h2 id="password-header">Password</h2>
                                <FormInput
                                    id="loginPassword"
                                    type="text"
                                    name="loginPassword"
                                    value={this.state.loginPassword}
                                    handleChange={this.handleChange}
                                    required
                                />
                                <button id="login-button" type="submit">Login</button>
                            </form>
                        </React.Fragment>
                    </div>
                </div>
                <div className="register">
                    <h2 id="login-header">Register</h2>
                    <div id="login-form-div">
                        <React.Fragment>
                            <form id="register-form" onSubmit={this.handleRegister}>
                                <h2 id="username-header">Username</h2>
                                <FormInput
                                    id="registerUsername"
                                    type="text"
                                    name="registerUsername"
                                    value={this.state.registerUsername}
                                    handleChange={this.handleChange}
                                    required
                                />
                                <h2 id="password-header">Password</h2>
                                <FormInput
                                    id="registerPassword"
                                    type="text"
                                    name="registerPassword"
                                    value={this.state.registerPassword}
                                    handleChange={this.handleChange}
                                    // label="password"
                                    required
                                />
                                {/* <Link to ="/authenticate">
                                <button id="login-button" type="submit">Connect to Spotify</button>
                                </Link> */}
                                <div id="button-container">
                                    <SpotifyAuth 
                                        id="login-button"
                                        redirectUri='http://localhost:3000/authenticate'
                                        clientID='35a2f5b326314bf381975f4e5d4dcc1e'
                                        scopes={[Scopes.userReadPrivate, Scopes.userReadEmail, Scopes.userTopRead]}
                                        onAccessToken={this.handleAccessToken}
                                    />
                                </div>
                            </form>
                        </React.Fragment>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;