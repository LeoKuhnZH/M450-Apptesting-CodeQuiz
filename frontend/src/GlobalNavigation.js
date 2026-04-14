import React from 'react';
import { Link } from 'react-router-dom';

class GlobalNavigation extends React.Component {
  constructor(props) {
    super(props);

    
    this.radio = new Audio(
      "https://energyzuerich.ice.infomaniak.ch/energyzuerich-high.mp3"
    );
  }

  playRadio = () => {
    this.radio.load();
    this.radio.play().catch(err => console.log(err));
  };


  stopRadio = () => {
    this.radio.pause();
    this.radio.currentTime = 0;
  };

  render() {
    return (
      <nav>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/quiz">Quiz</Link></li>
          <li><Link to="/rules">Rules</Link></li>
          <li><Link to="/questionslist">Questions</Link></li>
          <li><Link to="/aboutus">About Us</Link></li>
        </ul>

        <div className="radio-controls">
          <button onClick={this.playRadio}>▶ Play Radio</button>
          <button onClick={this.stopRadio}>⏹ Stop</button>
        </div>
      </nav>
    );
  }
}

export default GlobalNavigation;