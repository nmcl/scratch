for ( i=0; i<2e3; )                             // for loop head
x.fillStyle = R( 99*i, 2*i, i, i?1:.4 ),        // set color
x.fillRect(                                     // draw rectangle
  i? 960 + i*S( F = 260*(t+9)/i + S(i*i) ) : 0, // X coord
  i? 500 + .2 * ( 2*i*C(F) + 2e4/i ) : 0,       // Y coord
  K = i++? S(i)*9 : 2e3, K)                     // width, height 
  // from http://frankforce.com/?p=6378
