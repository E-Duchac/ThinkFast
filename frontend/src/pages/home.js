import React from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import PhotoCarousel from '../components/PhotoCarousel';

const Home = () => {
    const navigate = useNavigate();

    const handleClick = (type) => {
      //alert("You clicked " + type + "!");
      navigate('/setupQuiz', {state: {userType: type}});
    }

    return (
    <div className="App">
      <Header />
      <div className="App-body">
        <PhotoCarousel />
          <div className="login-panel">
            <p><a href="http://localhost:3000/login">Log in,</a> or choose one to get started!</p>
              <div>
                <button value="student" onClick={(e) => handleClick(e.target.value)}>I'm a student!</button>
                <button value="adult" onClick={(e) => handleClick(e.target.value)}>I'm an adult!</button>
              </div>
          </div>
      </div>
      <Footer />
    </div>
    );
};

export default Home;