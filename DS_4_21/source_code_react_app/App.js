import React, { Component } from 'react';
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sensors: []
        }
    }
 //Calling this method every 40 s to retrive the senosr data from rest api
    async componentDidMount() {
        try {
            setInterval(async () => {
                const url = "http://localhost:8080/sensor";
                fetch(url)
                    .then(response => response.json())
                    .then(json => this.setState({ sensors: json }))
            }, 40000);
        } catch(e) {
            console.log(e);
        }
    }
     //checking whether the smoke level or/and is above or equal to 5 and produce alert boxes
    highSml(smokelevel,co2){
        //alert('smoke level is high');
        if (smokelevel>=5 || co2>=5){
            alert('Sensor is exceeding limits');
        }
    }
    render() {
        const { sensors } = this.state;
        return (

            <div className="container">
                <div className="jumbotron">
                    <h1 className="display-4">Sensor Details</h1>
                </div>
                {sensors.map((post) => (
                    <div className="card" key={post.location}>
                        <div className="card-header">
                            <table class="table table-striped table-bordered">

                                <th>Location</th>
                                <th>Activity</th>
                                <th>Smoke Level</th>
                                <th>Co2 Level</th>

                                <tr>
                                    <td>{post.location}</td>
                                    <td>{post.activity}</td>
                                    <td>{post.smokelevel}</td>
                                    <td>{post.co2}</td>
                                </tr>

                            {this.highSml(post.smokelevel,post.co2)}
                            </table>
                        </div>

                    </div>
                ))}
            </div>
        );
    }
}
export default App;
