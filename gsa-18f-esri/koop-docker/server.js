var express = require('express');
var cors = require('cors');
var config = require('config');
config.fda = {key: process.env.fda_key};
config.db = {pg: process.env.db};
var koop = require('koop')(config);
var esCache = require('koop-escache');
var fda = require('koop-fda');
var gist = require('koop-gist');
var pgCache = require('koop-pgcache');
var path = require('path');
var app = express();
var server;

var http = require('http');

var koopCacheBackend = process.env.KOOP_CACHE || ""

// Register the cache backend provided by KOOP_CACHE.
if(koopCacheBackend == "postgis") {
  config.db.conn = config.db.pg;
  koop.registerCache(pgCache);
}
else if(koopCacheBackend == "es") {
  config.db.conn = config.db.es;
  koop.registerCache(esCache);
}
else {
  console.log("No persistent cache set. Using in-memory cache storage.")
};

koop.register(fda);
koop.register(gist);

app.set('port', process.env.PORT || config.server.port || 9000);

app.use(cors());
app.use(koop);

app.get('/status', function (req, res) {
  res.json(koop.status);
});

app.get('/', function (req, res) {
  res.json(koop.status);
});

server = http.createServer(app);

server.listen(app.get('port'), function () {
  console.log('Express koop server listening on port ' + app.get('port'));
})
