import React from 'react';

const Home = () => {
    //const [userType, setUserType] = useState('');

    function handleClick({userType}) {
        alert("You clicked " + userType + "!");
    }

    return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome to ThinkFast!</h1>
        <p>
          <i>Quick Recall practice, from Academic Team to Trivia Night</i>
        </p>
      </header>
      <body className="App-body">
        <div className="photo-carousel">
          <image>Placeholder for photo carousel</image>
          <div className="login-panel">
            <p><a href="http://localhost:3000/login">Log in,</a> or choose one to get started!</p>
              <div>
                <button onClick={handleClick} setUserType="student">I'm a student!</button>
                <button onClick={handleClick} setUserType="adult">I'm an adult!</button>
              </div>
          </div>
        </div>

      </body>
      <footer className="App-footer">
          <p>All content © 2024 ThinkFast.com. All Rights Reserved</p>
      </footer>
    </div>
    );
};

export default Home;