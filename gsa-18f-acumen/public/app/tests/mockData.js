var mockData = (function() {
	return {
		getMockRecalls: getMockRecalls,
		getMockConfig: getMockConfig,
		getMockSearchParams: getMockSearchParams,
		getMockSearchServiceCall: getMockSearchServiceCall,
		getFakeHttpParams: getFakeHttpParams,
		getMockSearchServiceCallError: getMockSearchServiceCallError
	};

	function getMockRecalls() {
		return {
		  "meta": {
		    "disclaimer": "openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated.",
		    "license": "http://open.fda.gov/license",
		    "last_updated": "2015-05-31",
		    "results": {
		      "skip": 0,
		      "limit": 10,
		      "total": 1173
		    }
		  },
		  "results": [
		    {
		      "recall_number": "F-1590-2014",
		      "reason_for_recall": "Reports of mild flushing reaction after consumption of product led to concern of possible production issue.  Flushing condition resolved itself within two hours.",
		      "status": "Ongoing",
		      "distribution_pattern": "Nationwide",
		      "product_quantity": "none since prior to the recall",
		      "recall_initiation_date": "20140206",
		      "state": "CA",
		      "event_id": "67465",
		      "product_type": "Food",
		      "product_description": "Uncle Ben's (R), Infused(R) Rice Cheese Flavor, net weight 5 LB (2.27 kg), Distributed by MARS foodservices US, P.O. Box 11547, Carson, CA 90749-1547",
		      "country": "US",
		      "city": "Rancho Dominguez",
		      "recalling_firm": "Mars Food US",
		      "report_date": "20140305",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class II",
		      "code_info": "All codes. NOTE: The firm placed this product on their recall list as a precaution as although the shelf life is one year, it could still be on the market.",
		      "@id": "00745947a0072e9f2cc64f74b9e127f880561af98b58bd21226eb553359cfc9f",
		      "openfda": {},
		      "initial_firm_notification": "Telephone"
		    },
		    {
		      "recall_number": "F-0359-2014",
		      "reason_for_recall": "Manufacturing defect leading to post-process\\\ncontamination.",
		      "status": "Ongoing",
		      "distribution_pattern": "Nationwide and to Korea, China, Mexico, Hong Kong, Singapore, Malaysia, Costa Rica, jordan, Bermuda, Indonesia and United Kingdom",
		      "product_quantity": "597,827 cases total, all varieties.",
		      "recall_initiation_date": "20131108",
		      "state": "CA",
		      "event_id": "66795",
		      "product_type": "Food",
		      "product_description": "Plum Organics Cherry & Sweet Corn Organic Baby Food, 4 oz. pouch. \\\n\\\nFlexible pouch with Resealable spout.\\\nPackaged for individual sale in 6 count cartons and in 4 count packages.\\\nBest By dates ranging from 08/05/14 to 12/08/14 and the letters AT.\\\n\\\nDISTRIBUTED BY:\\\nNEST COLLECTIVE, INC.\\\nEMERYVILLE, CA 94608\\\n1-877-914-PLUM\\\nWWW.NEST-COLLECTIVE.COM",
		      "country": "US",
		      "city": "Emeryville",
		      "recalling_firm": "Plum Inc",
		      "report_date": "20131204",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class II",
		      "code_info": "Item number 1382:\\\nSelling Unit UPC: 846675001344 ;            \\\nCase UPC:846675001382 ;\\\nBest By dates ranging from 08/05/14 to 12/08/14 and the letters AT.",
		      "@id": "00842ef8b93853979cd74c5f8a198a912acdb2e4e5462000f7bb340173654478",
		      "openfda": {},
		      "initial_firm_notification": "Two or more of the following: Email, Fax, Letter, Press Release, Telephone, Visit"
		    },
		    {
		      "recall_number": "F-0626-2013",
		      "reason_for_recall": "The firm inititated the recall of Crunch  thinkThin nutrition bars which may contain blanched roasted peanuts supplied by Sunland , Inc and have the potential to be contaminated with Salmonella.",
		      "status": "Ongoing",
		      "distribution_pattern": "US states nationwide. No international distribution.",
		      "product_quantity": "2,408 cases",
		      "recall_initiation_date": "20121012",
		      "state": "CA",
		      "event_id": "63473",
		      "product_type": "Food",
		      "product_description": "Nutrition bar. Crunch  thinkThin Brand, Caramel Chocolate Dipped Mixed Nuts, 40 grams. Packaged in plastic, labeled as:\"Distributed by thinkproducts***Ventura, CA 93003***\".\\\n\\\n10 bars/carton, 12 cartons/case. 120 bars per case",
		      "country": "US",
		      "city": "Ventura",
		      "recalling_firm": "Think Thin, LLC",
		      "report_date": "20121114",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class I",
		      "code_info": "UPC Code: 7 53656 70879 9\\\nBest by Dates: 112812, 030813, 040513, 042113, 051013, 060113, 061513, 071313 AND expired lots prior to Best By 101012",
		      "@id": "00b0a3386336e5d43322bb4182b9e0a710c34d2202e3c19caeae9109f17dda7e",
		      "openfda": {},
		      "initial_firm_notification": "Press Release"
		    },
		    {
		      "recall_number": "F-1542-2012",
		      "reason_for_recall": "Red Chambers is recalling Dai One Food Oyster IQF 4lbs, 25 lbs, and 8 oz because the products have been prepared, packed, or held under insanitary conditions whereby it may have been contaminated with filth, or whereby it may have been rendered injurious to health.",
		      "status": "Ongoing",
		      "distribution_pattern": "California and Floria",
		      "product_quantity": "1) 83 units; 2) 800 units; 3) 13 units",
		      "recall_initiation_date": "20120618",
		      "state": "CA",
		      "event_id": "62339",
		      "product_type": "Food",
		      "product_description": "Frozen Korean Oyster Meat I.Q.F.:  , 1) 40x8oz., UPC 19964 30091; 2) 1x25lbs, .; 3) 5x4lbs.UPC 19964 30092",
		      "country": "US",
		      "city": "Vernon",
		      "recalling_firm": "Red Chamber Company",
		      "report_date": "20120627",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class II",
		      "code_info": "1) lot#138338P; 2) lot#138339-P; 3) lot #138340-P",
		      "@id": "00ceccbf414d981afd26593040fdc9c149194b8a59d7e42386147ddc0df3aba9",
		      "openfda": {},
		      "initial_firm_notification": "Letter"
		    },
		    {
		      "recall_number": "F-1914-2015",
		      "reason_for_recall": "This recall is being initiated due to test results that show the presence of  Listeria monocytogenes in product that was shipped erroneously.  Other finished product test results show the presence of Listeria but was not speciated, and out precaution those lots are also included in this recall. \\\n\\\n Listeria monocytogenes is an organism which can cause serious and sometimes fatal infections in young children, frail or elderly people, and others with weakened immune systems. Although healthy individuals may suffer only short-term symptoms such as high fever, severe headache, stiffness, nausea, abdominal pain and diarrhea, Listeria infection can cause miscarriages and stillbirths among pregnant women.  \\\n\\\nThe issue was discovered during a review of our microbiological testing procedures.",
		      "status": "Ongoing",
		      "distribution_pattern": "The firm has customers located in the U.S. only in the following states; CA, OR, WA, and WI.",
		      "product_quantity": "30,800 lbs.",
		      "recall_initiation_date": "20150320",
		      "state": "CA",
		      "event_id": "70778",
		      "product_type": "Food",
		      "product_description": "-Frozen IQF 1/2 Chopped Spinach\\\n\\\nPacked in Bulk in 40 lb cases\\\n\\\nEach case is tagged with a label containing the following information, item number, lot code, production date, weight, and SKU number.\\\n\\\n00SP0241T10\\\nOrganic Spinach Cut Leaf\\\nLot #\\\n1431930067\\\nQuantity: 1\\\nNet Weight: 1,000 lbs\\\nProd Date 11/15/2014\\\nProduct of USA\\\nCoastal Green Vegetable Co., LLC\\\nOxnard, CA 93030\\\nProd Date\\\n11/15/2014\\\n1,000.00 lbs\\\n1431930067\\\nX0SPO241110 Organic Spinach Cut Leaf",
		      "country": "US",
		      "city": "Oxnard",
		      "recalling_firm": "Coastal Green Vegetable Company, LLC",
		      "report_date": "20150415",
		      "@epoch": 1429310080.242206,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class II",
		      "code_info": "Each tote and/or case receives an individual lot number.\\\n\\\nLot/Serial Number: 1434710015, 1434710016, 1434710017, 1434710018, 1434710019, 1434710020, 1434710021, 1434710022, 1434710023, 1434710024, 1434710025, 1434710026, 1434710027, 1434710028, 1434710029, 1434710030, 1434710031, 1434710032, 1434710033, 1434710034, 1434720035, 1434720036",
		      "@id": "00f4522754982e18a4038b73f7843796a1d7dd838fec5ee553efdc0fc3367935",
		      "openfda": {},
		      "initial_firm_notification": "Two or more of the following: Email, Fax, Letter, Press Release, Telephone, Visit"
		    },
		    {
		      "recall_number": "F-1793-2012",
		      "reason_for_recall": "Pacific American Fish Company (PAFCO) is recalling recalling Korean Oysters because the product has been prepared, packed, or held under insanitary conditions whereby it may have become contaminated with filth, or whereby it may have been rendered injurious to health.",
		      "status": "Ongoing",
		      "distribution_pattern": "Nationwide in the US",
		      "product_quantity": null,
		      "recall_initiation_date": "20120628",
		      "state": "CA",
		      "event_id": "38284",
		      "product_type": "Food",
		      "product_description": "Oyster Half Shell Farm, OYS107, 144ps/cs,\\\nOyster Half Shell small Farm, OYS114, 144ps/cs,",
		      "country": "US",
		      "city": "Vernon",
		      "recalling_firm": "Pacific American Fish Co Inc",
		      "report_date": "20120801",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class II",
		      "code_info": "PAFCO Code: OYS107, Lot# 202493-1, 204395-1, 210108-1, 211049-1, 211050-1, 218908-1, 219897-1.\\\n\\\nPAFCO Code: OYS114, Lot # 204395-2, 205669-1, 218908-2, 219897-1.",
		      "@id": "0135f00c297d8c51e908fba91e080255ca73f3e25100eb5b24bfe28f7d259d4e",
		      "openfda": {},
		      "initial_firm_notification": "Letter"
		    },
		    {
		      "recall_number": "F-1492-2014",
		      "reason_for_recall": "Natures Best is recalling Kinnikinnick pancake and waffle products due to undeclared milk.",
		      "status": "Ongoing",
		      "distribution_pattern": "Nationwide in US",
		      "product_quantity": "538 units",
		      "recall_initiation_date": "20140113",
		      "state": "CA",
		      "event_id": "67352",
		      "product_type": "Food",
		      "product_description": "Panko Style Bread Crumbs, 12.5 oz, UPC 620133600153, NB Item #636886",
		      "country": "US",
		      "city": "Chino",
		      "recalling_firm": "Natures Best Inc",
		      "report_date": "20140219",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class I",
		      "code_info": "Lot codes: \\\n2013NO25 20131125 131125\\\n2013NO26 20131126 131126\\\n2013DE03 20131203 131203\\\n2013DE11 20131211 131211\\\n2013DE22 20131222 131222\\\n2013DE23 20131223 131223\\\n2013DE24 20131224 131224\\\n2013DE30 20131230 131230\\\n2014JA04 20140104 140104\\\n2014JA11 20140111 140111\\\n2014JA15 20140115 140115\\\n2014JA19 20140119 140119\\\n2014JA28 20140128 140128\\\n2014JA29 20140129 140129\\\n2014FE02 20140202 140202\\\n2014FE09 20140209 140209\\\n2014FE11 20140211 140211\\\n2014FE19 20140219 140219\\\n2014FE22 20140222 140222\\\n2014MR11 20140311 140311\\\n2014MR30* 2014AL27* 20140427*\\\n2014AL15* 2014AL16* 2014AL20*\\\n2014MA19* 2014JN11* 2014JL23*\\\n2014MA21* 2014JN22* 2014NO28*\\\n2014MA27* 2014JN23* 2014JL29*\\\n2014JN02* 2014JL01* 2014AU04*\\\n2014JN03* 2014JL05* 2014AU05*\\\n2014JN04* 2014JL15* 2017JN07*\\\n2014JL21*",
		      "@id": "0139f0b319130322fee98626efc9132068c63ba605056226abc0b055eafff18a",
		      "openfda": {},
		      "initial_firm_notification": "Letter"
		    },
		    {
		      "recall_number": "F-1782-2012",
		      "reason_for_recall": "Arctic Zero Inc. is recalling their Arctic Zero Frozen Dessert products due to undeclared milk.",
		      "status": "Ongoing",
		      "distribution_pattern": "Nationwide in US",
		      "product_quantity": "2,303,112 units of pints total",
		      "recall_initiation_date": "20120706",
		      "state": "CA",
		      "event_id": "51819",
		      "product_type": "Food",
		      "product_description": "Pumpkin Spice, 1pt., UPC 8 52244 00307 7",
		      "country": "US",
		      "city": "Escondido",
		      "recalling_firm": "Arctic Zero, Inc.",
		      "report_date": "20120801",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class I",
		      "code_info": "Production dates 1/1/11 - 2/29/12",
		      "@id": "014af8a29b569d98cdcb6f829767de586042ab52f84ab0e5f841e09b746d8258",
		      "openfda": {},
		      "initial_firm_notification": "Press Release"
		    },
		    {
		      "recall_number": "F-0920-2013",
		      "reason_for_recall": "State of CA inspection found four products do not declare allergy statements which reflect the use of milk, wheat and eggs.",
		      "status": "Ongoing",
		      "distribution_pattern": "CA, WA  and MD",
		      "product_quantity": "471 retail, 3723 5 lb balls.",
		      "recall_initiation_date": "20121221",
		      "state": "CA",
		      "event_id": "63956",
		      "product_type": "Food",
		      "product_description": "Falafel with Tahini Sauce;\\\nnuggets of ground garbanzo beans with a rich, tangy tahini sauce.\\\nSold in 5 oz retail rectangular deli containers and 5 lb bulk foodservice.\\\nUndeclared wheat\\\nManufactured by Haigs Delicacies, Hayward, CA.",
		      "country": "US",
		      "city": "Hayward",
		      "recalling_firm": "Haig's Delicacies",
		      "report_date": "20130130",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class II",
		      "code_info": "all products",
		      "@id": "017aecd69cc95d241a7b9d0196a02d2f99563f026b15355ba6117e0f9b982a7f",
		      "openfda": {},
		      "initial_firm_notification": "Press Release"
		    },
		    {
		      "recall_number": "F-1611-2012",
		      "reason_for_recall": "River Ranch was notified that two separate samples of products pulled by the FDA which were produced by River Ranch, tested positive for Listeria monocytogenes.",
		      "status": "Ongoing",
		      "distribution_pattern": "Products were released for distribution in US and Canada.",
		      "product_quantity": null,
		      "recall_initiation_date": "20120518",
		      "state": "CA",
		      "event_id": "61901",
		      "product_type": "Food",
		      "product_description": "Cross Valley brand Iceberg Salad Mix, 4 x 5lb bag, UPC: n/a; Product is a salad item; bagged in clear polyethylene film (foodservice) and polypropylene/polyethylene (retail). Refrigerate and consume within Best By date. Product is processed and packaged by River Ranch Fresh Foods, LLC",
		      "country": "US",
		      "city": "Salinas",
		      "recalling_firm": "River Ranch Fresh Foods  LLC",
		      "report_date": "20120815",
		      "@epoch": 1424553174.836488,
		      "voluntary_mandated": "Voluntary: Firm Initiated",
		      "classification": "Class I",
		      "code_info": "Best By code dates between 12MAY2012 - 29MAY2012; 12MAY2012 - 22MAY2012. The code date is located in the upper right hand corner of the bags.",
		      "@id": "01a2ab7ed66eed3b092cc72102f5a278df26d0eaa071a6dda65c4820e1a304e9",
		      "openfda": {},
		      "initial_firm_notification": "Press Release"
		    }
		  ]
		};
	}

	function getMockConfig() {
		return {
	                "restServiceBaseURL": "/api/food/search",
	                "recallLookups": {
	                    "statusLookups": ["Ongoing"],
	                    "classificationLookups": ["Class I", "Class II", "Class III"],
	                    "stateLookups": ["AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"]
	                },
	                "knownApiErrorCodes": {
						"NOT_FOUND": "No matches found!"
					},
	                setupConfig: function () {}
	            };
	}

	function getMockSearchParams() {
		return {
			searchTerm: 'some search',
			status: 'Ongoing',
			classification: 'Class I',
			state: 'OH'
		};
	}

	function getMockSearchServiceCall(){
		return {
			config: {},
			data: getMockRecalls(),
			status: 200,
			statusText: "OK"
		};
	}

	function getMockSearchServiceCallError() {
		return {
			config: {},
			data: {
				error: {
					code: "NOT_FOUND",
					message: "No matches found!"
				}
			},
			status: 200,
			statusText: "OK"
		};
	}

	function getFakeHttpParams() {
		var params = getMockSearchParams();
		return {
			searchTerm: params.searchTerm,
            status: params.status,
            classification: params.classification,
            state: params.state,
            page: 1
		};
	}
})();