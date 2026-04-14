import React from 'react';
import logo from "./components/Bilder/Wer wird Millionär Wiss Quiz Edition (2).png";

class Home extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                
                <img
                    src={logo}
                    alt="Wer wird Millionär Wiss Quiz Edition (2).png"
                    style={{ width: "500px", marginBottom: "20px" }}
                />

                <p>This is a one-page app which demonstrates the power of the React.js framework.</p>
            </div>
        )
    }
}

export default Home;