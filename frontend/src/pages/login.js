import Header from '../components/Header';
import Footer from '../components/Footer';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { login } from '../apis/UserAPI';

const Login = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [token, setToken] = useState();

    const loginUser = async (event) => {
        event.preventDefault();
        const setToken = await Promise.resolve(login(username, password));
        console.log("Token: " + token);
        //set username, profile, etc.
        //navigate
    }

    return (
        <div className="App">
            <Header />
            <div className="App-body">
                <p>Stubbed login page. <a href="http://localhost:3000/">Go back.</a></p>
                <div className="login-panel">
                    <form className="login-form" onSubmit={loginUser}>
                        <input type="text" value={username} onChange={e => setUsername(e.target.value)}></input>
                        <input type="text" value={password} onChange={e => setPassword(e.target.value)}></input>
                        <button type="submit">Login</button>
                    </form>
                </div>
            </div>
            <Footer />
        </div>
    );
}

export default Login;