import React from 'react';
import Title from '@components/title';
import Navbar from '@components/navbar';

function Stuff() {
  return (
    <div>
      <Title />
      <Navbar page={'stuff'} />
      <div>
        <h3>STUFF</h3>
        <p>Phasellus ultrices nulla quis nibh. Quisque a lectus. Donec consectetuer ligula vulputate sem tristique cursus. Nam nulla quam, gravida non, commodo a, sodales sit amet, nisi.</p>
        <ol>
          <li>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</li>
          <li>Proin vulputate ex non mauris placerat efficitur.</li>
          <li>Mauris non arcu a nisi rhoncus luctus sed eu sem.</li>
          <li>Sed in elit non erat cursus lacinia eu a lectus.</li>
          <li>Donec sed ligula ac turpis feugiat pretium ac eu augue.</li>
        </ol>
      </div>
    </div>
  );
}

export default Stuff;
