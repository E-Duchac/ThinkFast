// import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        {/* <img src={logo} className="App-logo" alt="logo" /> */}
        <h1>Welcome to ThinkFast!</h1>
        <p>
          {/* Edit <code>src/App.js</code> and save to reload. */}
          <i>Quick Recall practice, for Academic Team to Trivia Night</i>
        </p>
        {/* <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a> */}
      </header>
      <body className="App-body">
        <div className="photo-carousel">
          <image>Placeholder for photo carousel</image>
        </div>

        <p>Log on, or choose one to get started!</p>

        <div className="button-panel">
            <button>I'm a student!</button>
            <button>I'm an adult!</button>
        </div>
      </body>
      <footer className="App-footer">
          <p>All content © 2024 ThinkFast.com. All Rights Reserved</p>
      </footer>
    </div>
  );
}

export default App;
