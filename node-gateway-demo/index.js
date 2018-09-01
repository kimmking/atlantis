const http = require('http'); 
const request = require('request');

var proxyService = "http://localhost:8088";

var _request = request.defaults({
  timeout: 500, 
  forever: true,
  pool: {maxSockets: 1000}
})

var server = http.createServer(function (req, res) {   

	_request(proxyService + req.url, { json: false }, (err, response, body) => {
	  if (err) { return console.log(err); }
	  res.writeHead(response.statusCode,response.headers);
      res.write(body);
      res.end();
	  
	});
 
});
 
server.listen(5000); 
console.log('Node.js proxy server at port 5000 is running..')