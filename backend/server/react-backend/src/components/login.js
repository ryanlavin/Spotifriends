import React from "react";
import "../css/login.css";
import FormInput from "./input-component";
//import '../css/login.css';
import axios from 'axios';
import {Redirect, Link} from "react-router-dom";
import {SpotifyAuth, Scopes} from 'react-spotify-auth';
import 'react-spotify-auth/dist/index.css';
import Cookies from 'js-cookie';

const authButton = 
<>
<SpotifyAuth 
    id="login-button"
    redirectUri='http://localhost:3000/authenticate'
    clientID='35a2f5b326314bf381975f4e5d4dcc1e'
    scopes={[Scopes.userReadPrivate, Scopes.userReadEmail, Scopes.userTopRead]}
    /* onAccessToken={this.handleAccessToken} */
/>
</>
var styles = {
    backgroundColor:'grey',
    borderRadius:'10px',
    color:'white',
    fontWeight:'600',
    fontSize:'16px',
    height:'40px',
    width:'200px',
};
const dummyButton = 
    <div id="dummytext" style={styles}>
        Please enter registration info
    
    </div>

class Login extends React.Component {
    constructor(props) {
        super(props);

        
        this.state = {
            fieldsFilled: false,
            loginUsername: "",
            loginPassword: "",
            registerUsername: "",
            registerPassword: "",
            access_token:"",
            loggedIn:false,
        };
    }


    showButton(){
        if(this.state.fieldsFilled){
            return authButton;
        }
        else{return dummyButton}
    }
    handleLogin = (event) => {
        event.preventDefault();
        axios.post("http://localhost:8080/login-api",
            {
                username: Cookies.get('uname'),
                password: Cookies.get('pw')
            }).then((response) => {
                console.log(response);
                Cookies.set('sessionID', response.data.session);
                this.setState({ loginUsername: "", loginPassword: "",loggedIn:true });
            }).then((error) => {
                console.log(error);
            })
        
        
    };

    handleChange = (event) => {
        const { value, name } = event.target;

        this.setState({ [name]: value },()=>{
            if(this.state.registerUsername.length>5
            && this.state.registerPassword.length>5){
                Cookies.set('uname',this.state.registerUsername);
                Cookies.set('pw',this.state.registerPassword);
                this.setState({fieldsFilled:true})
                console.log("uname cookie:" + Cookies.get('uname') + "  pw cookie:" + Cookies.get('pw')  );

            }
            else if(this.state.fieldsFilled){
                this.setState({fieldsFilled:false})
            }
        });
    };
    handleLoginChange = (event) => {
        const { value, name } = event.target;

        this.setState({ [name]: value },()=>{
            if(this.state.loginUsername.length>5
            && this.state.loginPassword.length>5){
                Cookies.set('uname',this.state.loginUsername);
                Cookies.set('pw',this.state.loginPassword);
                console.log("uname cookie:" + Cookies.get('uname') + "  pw cookie:" + Cookies.get('pw')  );

            }
        });
    };
    handleRegister = (event) => {
        event.preventDefault();
        axios.post("http://localhost:8080/register-api",
        {
            username: this.state.registerUsername,
            password: this.state.registerPassword,
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
    redirect(){
        if(this.state.loggedIn){
            return <Redirect push to="/dashboard"/>
        }
    }
    render() {
        return (
            <div className="sign-in">
                {this.redirect()}
                <div className="login">
                    <h2 id="login-header">Login</h2>
                    <div id="login-form-div">
                        <React.Fragment>
                            <form id="login-form" >
                                <h2 id="username-header">Username</h2>
                                <FormInput
                                    id="loginUsername"
                                    type="text"
                                    name="loginUsername"
                                    value={this.state.loginUsername}
                                    handleChange={this.handleLoginChange}
                                    required
                                />
                                <h2 id="password-header">Password</h2>
                                <FormInput
                                    id="loginPassword"
                                    type="text"
                                    name="loginPassword"
                                    value={this.state.loginPassword}
                                    handleChange={this.handleLoginChange}
                                    required
                                />
                                <button id="login-button" type="submit" onClick={this.handleLogin}>Login</button>
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
                                    ref
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
                                 {this.state.fieldsFilled ? authButton : dummyButton}   
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