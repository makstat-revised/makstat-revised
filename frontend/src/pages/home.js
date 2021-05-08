import React from 'react';
import axios from '@src/axios.config';
import Title from '@components/title';
import Navbar from '@components/navbar';
import Dashboard from '@components/Dashboard';

function Home() {

  React.useEffect(() => {
    axios.get('')
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      })
  }, []);

{debugger;
  return (
    <div>
      <Title />
      <Navbar page={'home'} />
      <Dashboard />
      <div>
        <h3>HELLO</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
      
      </div>
    </div>

  );
}};

export default Home;
