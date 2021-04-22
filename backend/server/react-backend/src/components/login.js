import React from "react";
import "../css/login.css";
import FormInput from "./input-component";
//import '../css/login.css';

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
    };
  }

  handleSubmit = (event) => {
    event.preventDefault();
    this.setState({ username: "", password: "" });
  };

  handleChange = (event) => {
    const { value, name } = event.target;

    this.setState({ [name]: value });
  };

  render() {
    return (
      <div className="sign-in">
        <div className="login">
            <h2 id="login-header">Login</h2>
            <div id="login-form-div">
                <React.Fragment>
                <form id="login-form" onSubmit={this.handleSubmit}>
                    <h2 id="username-header">Username</h2>
                    <FormInput
                    id="username"
                    type="text"
                    name="username"
                    value={this.state.username}
                    handleChange={this.handleChange}
                    // label="username"
                    required
                    />
                    <h2 id="password-header">Password</h2>
                    <FormInput
                    id="password"
                    type="text"
                    name="password"
                    value={this.state.password}
                    handleChange={this.handleChange}
                    // label="password"
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
                <form id="login-form" onSubmit={this.handleSubmit}>
                    <h2 id="username-header">Username</h2>
                    <FormInput
                    id="username"
                    type="text"
                    name="username"
                    value={this.state.username}
                    handleChange={this.handleChange}
                    // label="username"
                    required
                    />
                    <h2 id="password-header">Password</h2>
                    <FormInput
                    id="password"
                    type="text"
                    name="password"
                    value={this.state.password}
                    handleChange={this.handleChange}
                    // label="password"
                    required
                    />
                    <button id="login-button" type="submit">Connect to Spotify</button>
                </form>
                </React.Fragment>
            </div>
        </div>
      </div>
    );
  }
}

export default Login;
