 /**
 * author: venu duggireddy
 * description: Utility functions to suppprt API calls
 * version:0.0.1
 * Date:06/20/2015
 */
 
 var HashMap = require('hashmap'),
  _ = require('lodash-node'),
  config = require('./config'),
   path = require('path'),
   fs = require('fs'),
  utils = require('./utils');

// list of US states
var usstates = [
    {
        "name": "Alabama",
        "abbreviation": "AL"
    },
    {
        "name": "Alaska",
        "abbreviation": "AK"
    },
    {
        "name": "American Samoa",
        "abbreviation": "AS"
    },
    {
        "name": "Arizona",
        "abbreviation": "AZ"
    },
    {
        "name": "Arkansas",
        "abbreviation": "AR"
    },
    {
        "name": "California",
        "abbreviation": "CA"
    },
    {
        "name": "Colorado",
        "abbreviation": "CO"
    },
    {
        "name": "Connecticut",
        "abbreviation": "CT"
    },
    {
        "name": "Delaware",
        "abbreviation": "DE"
    },
    {
        "name": "District Of Columbia",
        "abbreviation": "DC"
    },
    {
        "name": "Federated States Of Micronesia",
        "abbreviation": "FM"
    },
    {
        "name": "Florida",
        "abbreviation": "FL"
    },
    {
        "name": "Georgia",
        "abbreviation": "GA"
    },
    {
        "name": "Guam",
        "abbreviation": "GU"
    },
    {
        "name": "Hawaii",
        "abbreviation": "HI"
    },
    {
        "name": "Idaho",
        "abbreviation": "ID"
    },
    {
        "name": "Illinois",
        "abbreviation": "IL"
    },
    {
        "name": "Indiana",
        "abbreviation": "IN"
    },
    {
        "name": "Iowa",
        "abbreviation": "IA"
    },
    {
        "name": "Kansas",
        "abbreviation": "KS"
    },
    {
        "name": "Kentucky",
        "abbreviation": "KY"
    },
    {
        "name": "Louisiana",
        "abbreviation": "LA"
    },
    {
        "name": "Maine",
        "abbreviation": "ME"
    },
    {
        "name": "Marshall Islands",
        "abbreviation": "MH"
    },
    {
        "name": "Maryland",
        "abbreviation": "MD"
    },
    {
        "name": "Massachusetts",
        "abbreviation": "MA"
    },
    {
        "name": "Michigan",
        "abbreviation": "MI"
    },
    {
        "name": "Minnesota",
        "abbreviation": "MN"
    },
    {
        "name": "Mississippi",
        "abbreviation": "MS"
    },
    {
        "name": "Missouri",
        "abbreviation": "MO"
    },
    {
        "name": "Montana",
        "abbreviation": "MT"
    },
    {
        "name": "Nebraska",
        "abbreviation": "NE"
    },
    {
        "name": "Nevada",
        "abbreviation": "NV"
    },
    {
        "name": "New Hampshire",
        "abbreviation": "NH"
    },
    {
        "name": "New Jersey",
        "abbreviation": "NJ"
    },
    {
        "name": "New Mexico",
        "abbreviation": "NM"
    },
    {
        "name": "New York",
        "abbreviation": "NY"
    },
    {
        "name": "North Carolina",
        "abbreviation": "NC"
    },
    {
        "name": "North Dakota",
        "abbreviation": "ND"
    },
    {
        "name": "Northern Mariana Islands",
        "abbreviation": "MP"
    },
    {
        "name": "Ohio",
        "abbreviation": "OH"
    },
    {
        "name": "Oklahoma",
        "abbreviation": "OK"
    },
    {
        "name": "Oregon",
        "abbreviation": "OR"
    },
    {
        "name": "Palau",
        "abbreviation": "PW"
    },
    {
        "name": "Pennsylvania",
        "abbreviation": "PA"
    },
    {
        "name": "Puerto Rico",
        "abbreviation": "PR"
    },
    {
        "name": "Rhode Island",
        "abbreviation": "RI"
    },
    {
        "name": "South Carolina",
        "abbreviation": "SC"
    },
    {
        "name": "South Dakota",
        "abbreviation": "SD"
    },
    {
        "name": "Tennessee",
        "abbreviation": "TN"
    },
    {
        "name": "Texas",
        "abbreviation": "TX"
    },
    {
        "name": "Utah",
        "abbreviation": "UT"
    },
    {
        "name": "Vermont",
        "abbreviation": "VT"
    },
    {
        "name": "Virgin Islands",
        "abbreviation": "VI"
    },
    {
        "name": "Virginia",
        "abbreviation": "VA"
    },
    {
        "name": "Washington",
        "abbreviation": "WA"
    },
    {
        "name": "West Virginia",
        "abbreviation": "WV"
    },
    {
        "name": "Wisconsin",
        "abbreviation": "WI"
    },
    {
        "name": "Wyoming",
        "abbreviation": "WY"
    },
    {
        "name" : "Nationwide",
        "abbreviation": "Nationwide"
    }
];

/*
    returns map of US states
 */
exports.recallstatemap = function(){
    var map = new HashMap();
    _.forEach(usstates, function(value){
        map.set(value.abbreviation.toUpperCase(), value.name);
      //  console.log("key %s and value %s", value.name, value.abbreviation);
    });
    return map;
};

/**
 * writes log
 */
exports.logrequest = function(request, response, next){
  var start = +new Date();
  var stream = process.stdout;
  var url = request.url;
  var method = request.method;

  response.on('finish', function(){
    var duration = +new Date() - start;
    var message = method + 'to' + url +
      '\ntook' + duration + 'ms \n\n';
    stream.write(message);
  });
};

/*
 ger the url based on product_type
*/
exports.getEnforcementUrl = function(product_type){
  var enforcement = '';
  if(_.isEmpty(product_type) || product_type === 'drug'){
    enforcement =   config.drug_enforcement_url;
  }else if (product_type === 'device') {
    enforcement =   config.device_enforcement_url;
  }else if(product_type === 'food'){
    enforcement =   config.food_enforcement_url;
  }
  return enforcement;

};

/**
 * aggregated results for map view
 */
exports.aggregateResults = function(results){

  var statesMap = utils.recallstatemap();
  var recallMap = new HashMap();
  var values = [];
  _.forEach(results, function(v){
  //  console.log('%O', v);
     var body;
      try {
        body = JSON.parse(v.body);
        var product = v.type;
        _.forEach(body.results, function(v, k){
          var term = v.term.toUpperCase();
          var count = v.count;
           if(statesMap.get(term)){
           //  console.log('State is %s and Count is %s and %s', term, count, product);
             if(recallMap.get(term)){
               var array = recallMap.get(term);
               array.push({
                 'type':product,
                 'count':count
               });
              recallMap.set(term,array);
             }else{
               recallMap.set(term, [{
                 'type':product,
                 'count':count
               }]);
             }
           }
        });
      } catch (e) {
        // some times parse is throwing exception have to verify
        console.log(e);
      }

  });
  recallMap.forEach(function(value, key) {
     //console.log(key + " : " + value);
     var total = 0;
     _.forEach(value, function(v){
        total = total + v.count;
     });
      var result = {
        state: key,
        'total':total,
        'value': value
      };
      values.push(result);
  });
  return values;
};

/**
 * aggregated results for list view
 * 
 */
 
exports.getAggegatedSearchResults = function (results) {
    var output = {
      meta: {},
      results: []
    };
   // output.meta = body.meta;
   
    _.forEach(results, function (result) {
       
        var body = JSON.parse(result.body);
        
        if (body.meta && output.meta.disclaimer) {
          output.meta.results.total = (output.meta.results.total + body.meta.results.total);
        } else {
          output.meta = body.meta || {};
        }
       
       
        _.forEach(body.results, function(v){
            var o = {
          recall_number: v.recall_number,
          recall_initiation_date: v.recall_initiation_date,
          product_description: v.product_description,
          code_info: v.code_info,
          recalling_firm: v.recalling_firm,
          state: v.state,
          city: v.city,
          country: v.country,
          distribution_pattern: v.distribution_pattern,
          reason_for_recall: v.reason_for_recall,
          classification: v.classification,
          product_quantity: v.product_quantity,
          openfda: v.openfda.unii,
          event_details: {
            event_id: v.event_id,
            product_type: v.product_type,
            status: v.status,
            recalling_firm: v.recalling_firm,
            state: v.state,
            city: v.city,
            country: v.country,
            recall_initiation_date: v.recall_initiation_date,
            voluntary_mandated: v.voluntary_mandated,
            distribution_pattern: v.distribution_pattern,
            initial_firm_notification: v.initial_firm_notification
          }
        };
        //console.log(o);
        output.results.push(o);
        });
       
    });
    return output;
};


/*
 * dynamically create config file for supprting DevOps
 */
exports.createConfigFiles = function (port) {
    require('dns').lookup(require('os').hostname(), function (err, add, fam) {
        //console.log('addr: '+add);
        var stream = fs.createWriteStream(path.join(__dirname + '/static/js/config1.js'));
          stream.once('open', function(fd) {
          stream.write("var global_constants = {\n");
          stream.write('"host_ip_address":"'+add+'",\n');
          stream.write('"host_port_number":"'+port+'"\n');
          stream.write("};\n");
          stream.end();
        });
  });
}

//module.exports = recallstatemap;
