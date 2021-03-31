import React from 'react';
import Title from '@components/title';
import Navbar from '@components/navbar';

function About() {
  return (
    <div>
      <Title />
      <Navbar page={'about'} />
      <div>
        <h3>About us</h3>
        <p>The easiest thing to do is post on our <a href="/">forums</a>.</p>
      </div>
    </div>
  );
}

export default About;
