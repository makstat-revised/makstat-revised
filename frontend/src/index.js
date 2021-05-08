import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';

// all scss files are imported thorugh the main.scss
// no other css/scss import has to be done anywhere else
import "@styles/main.scss";

import "./assets/scss/black-dashboard-react.scss";
// import "./assets/demo/demo.css";
import "./assets/css/nucleo-icons.css";

import Dashboard from "@components/Dashboard.js";
import "@fortawesome/fontawesome-free/css/all.min.css";

import ThemeContextWrapper from '@components/ThemeWrapper/ThemeWrapper';
import BackgroundColorWrapper from "@components/BackgroundColorWrapper/BackgroundColorWrapper";

import Home from '@pages/home';
import Stuff from '@pages/stuff';
import About from '@pages/about';
import AdminLayout from "@pages/Admin";
import routes from "./routes.js";

ReactDOM.render(
    <ThemeContextWrapper>
        <BackgroundColorWrapper>
            <Router basename={process.env.ROUTER_BASENAME}>
                <Route exact path="/">
                    <Home />
                </Route>
                <Route path="/stuff">
                    <Stuff />
                </Route>
                <Route path="/about">
                    <About />
                </Route>
                <Route path="/admin" > 
                    <AdminLayout />
                </Route>
                <Route path="/admin/dashboard" > 
                    <Dashboard />
                </Route>
                {/* <Redirect from="/admin" to="/admin/dashboard" />  */}
            </Router>
        </BackgroundColorWrapper>
    </ThemeContextWrapper>,
    document.getElementById('root')
);
