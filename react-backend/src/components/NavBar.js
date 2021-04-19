import React from 'react';
import { Link } from 'react-router-dom';
import "../css/NavBar.css";

function NavBar() {
    return (
        <div class="topnav">
            <Link class="logo" to="/">Spotifriends!</Link>
            <Link class="navButton" to="/profile">Profile</Link>
            <Link class="navButton" to="/login">Login/Register</Link>
            <Link class="navButton" to="/matching">Matching</Link>
            <Link class="navButton" to="/dashboard">Dashboard</Link>
        </div>
    )
}

export default NavBar;