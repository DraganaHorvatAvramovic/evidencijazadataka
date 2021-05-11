import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, HashRouter as Router, Switch } from 'react-router-dom';
import {Container, Button, Navbar, Nav} from "react-bootstrap"
import Login from "./components/login/Login"
import Home from './components/Home';
import NotFound from './components/NotFound';
import {logout} from './services/auth'
import Zadaci from './components/zadaci/Zadaci';
import AddZadaci from './components/zadaci/AddZadaci';
import EditZadatka from './components/zadaci/EditZadatka';


class App extends React.Component {
    render(){
        return (
            <div>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">Home</Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/zadaci">Zadaci</Nav.Link>

                    {window.localStorage['jwt'] ? 
                            <Button onClick = {()=>logout()}>Logout</Button> :
                            <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                    }

                    </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"25px"}}>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/zadaci" component={Zadaci}/>
                        <Route exact path="/zadaci/add" component={AddZadaci}/>
                        <Route exact path="/zadaci/edit/:id" component={EditZadatka}/>
                        <Route component={NotFound} />
                    </Switch>
                    </Container>
                </Router>
            </div>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.querySelector('#root')
);