import React from 'react';
import { Link } from 'react-router-dom';

function Navbar(props) {
    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark">
            <ul className="navbar-nav">
                <li className={props.page === 'home' ? 'nav-item active' : 'nav-item'}><Link className="nav-link" to="/">Home</Link></li>
                <li className={props.page === 'stuff' ? 'nav-item active' : 'nav-item'}><Link className="nav-link" to="/stuff">Stuff</Link></li>
                <li className={props.page === 'about' ? 'nav-item active' : 'nav-item'}><Link className="nav-link" to="/about">About</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;
