using System;
using System.Net;
using Newtonsoft.Json;
using NUnit.Framework;
using dsoft.ads.web.Models;

namespace dsoft.ads.web.Tests
{
	[TestFixture]
	public class OpenFDAQueryTest
	{
		private string json_result = @" 
			{
		      ""recall_number"": ""F-0283-2013"",
		      ""reason_for_recall"": ""During an FDA inspection, microbiological swabs were collected and the results found that 21 sub samples in zones 1, 2 & 3 are positive for Listeria Monocytogenes (L.M.), Listeria innocua (L.I.) or Listeria seeligeri (L.S.).  The firm is voluntarily recalling all products manufactured from August 20th to September 10th 2012 due to the possible contamination.  All products with sell by dates on or before 11-OCT. No illnesses have been reported."",
		      ""status"": ""Ongoing"",
		      ""distribution_pattern"": ""MI and OH only."",
		      ""product_quantity"": ""520"",
		      ""recall_initiation_date"": ""20120910"",
		      ""state"": ""MI"",
		      ""event_id"": ""63159"",
		      ""product_type"": ""Food"",
		      ""product_description"": ""#011 Zucchini Stir,Fry      0.75 pounds"",
		      ""country"": ""US"",
		      ""city"": ""Grand Rapids"",
		      ""recalling_firm"": ""Spartan Central Kitchen"",
		      ""report_date"": ""20121024"",
		      ""@epoch"": 1424553174.836488,
		      ""voluntary_mandated"": ""Voluntary: Firm Initiated"",
		      ""classification"": ""Class II"",
		      ""code_info"": ""All with sell by dates on or before 15-Sep with UPC 0-11213-90380"",
		      ""@id"": ""00028a950de0ef32fc01dc3963e6fdae7073912c0083faf0a1d1bcdf7a03c44c"",
		      ""openfda"": {},
		      ""initial_firm_notification"": ""E-Mail""
		    }";

		private string json_response = @"
			{
			  ""meta"": {
			    ""disclaimer"": ""openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated."",
			    ""license"": ""http://open.fda.gov/license"",
			    ""last_updated"": ""2015-05-31"",
			    ""results"": {
			      ""skip"": 0,
			      ""limit"": 1,
			      ""total"": 4161
			    }
			  },
			  ""results"": [
			    {
			      ""recall_number"": ""F-0283-2013"",
			      ""reason_for_recall"": ""During an FDA inspection, microbiological swabs were collected and the results found that 21 sub samples in zones 1, 2 & 3 are positive for Listeria Monocytogenes (L.M.), Listeria innocua (L.I.) or Listeria seeligeri (L.S.).  The firm is voluntarily recalling all products manufactured from August 20th to September 10th 2012 due to the possible contamination.  All products with sell by dates on or before 11-OCT. No illnesses have been reported."",
			      ""status"": ""Ongoing"",
			      ""distribution_pattern"": ""MI and OH only."",
			      ""product_quantity"": ""520"",
			      ""recall_initiation_date"": ""20120910"",
			      ""state"": ""MI"",
			      ""event_id"": ""63159"",
			      ""product_type"": ""Food"",
			      ""product_description"": ""#011 Zucchini Stir,Fry      0.75 pounds"",
			      ""country"": ""US"",
			      ""city"": ""Grand Rapids"",
			      ""recalling_firm"": ""Spartan Central Kitchen"",
			      ""report_date"": ""20121024"",
			      ""@epoch"": 1424553174.836488,
			      ""voluntary_mandated"": ""Voluntary: Firm Initiated"",
			      ""classification"": ""Class II"",
			      ""code_info"": ""All with sell by dates on or before 15-Sep with UPC 0-11213-90380"",
			      ""@id"": ""00028a950de0ef32fc01dc3963e6fdae7073912c0083faf0a1d1bcdf7a03c44c"",
			      ""openfda"": {},
			      ""initial_firm_notification"": ""E-Mail""
			    },
			    {
			      ""recall_number"": ""F-0924-2013"",
			      ""reason_for_recall"": ""The labels on two-liter batches of Regular and Diet Wild Cherry Pepsi were mistakenly swapped during production. This is potential safety issue for individuals who are sensitive to phenylalanine (i.e., phenylketonuric) or who are diabetic."",
			      ""status"": ""Ongoing"",
			      ""distribution_pattern"": ""Massachusetts, Connecticut, Maine, New Hampshire and Vermont"",
			      ""product_quantity"": ""1064 - 8 pack cases"",
			      ""recall_initiation_date"": ""20121220"",
			      ""state"": ""NY"",
			      ""event_id"": ""63954"",
			      ""product_type"": ""Food"",
			      ""product_description"": ""Regular Wild Cherry Pepsi two liter plastic bottles\nUPC 1231100"",
			      ""country"": ""US"",
			      ""city"": ""Valhalla"",
			      ""recalling_firm"": ""Pepsi Cola North America"",
			      ""report_date"": ""20130130"",
			      ""@epoch"": 1424553174.836488,
			      ""voluntary_mandated"": ""Voluntary: Firm Initiated"",
			      ""classification"": ""Class III"",
			      ""code_info"": ""Best Before Date of Feb2513 and a Manufacturing Code of PF11282"",
			      ""@id"": ""00051a008281b984d492fc73132fd514042daaa8facd7657b98a5bc33cc7d017"",
			      ""openfda"": {},
			      ""initial_firm_notification"": ""Letter""
			    }
			  ]
			}";

		public OpenFDAQueryTest ()
		{
		}

		[Test]
		public void ResponseParseTest()
		{
			OpenFDAResponse response = JsonConvert.DeserializeObject<OpenFDAResponse> (json_response);
			Assert.AreEqual (2, response.count);
			Assert.AreEqual ("2015-05-31", response.meta.last_updated);
			Assert.AreEqual ("20121220", response.results[1].recall_initiation_date);
		}

		[Test]
		public void ResultsParseTest()
		{
			OpenFDAResult response = JsonConvert.DeserializeObject<OpenFDAResult> (json_result);
			Assert.AreEqual ("F-0283-2013", response.recall_number);
			Assert.AreEqual ("MI", response.state);
		}

		[Test]
		public async void QueryTest()
		{

			OpenFDAQuery query = new OpenFDAQuery ();
			query.baseUrl = "https://api.fda.gov";
			query.apiKey = "iECPoOgnA6LVBZ5X3PHyx5Slpj1smHE6u4FuzlHl";
			query.source = OpenFDAQuery.FDAReportSource.food;
			query.type = OpenFDAQuery.FDAReportType.enforcement;
			bool result = await query.RunQueryAsync();

			Assert.IsTrue (result);

            /*
             * Note: mocked request TestWebRequest does not support async calls
			// test against a mock WebRequest
			WebRequest.RegisterPrefix ("test", new TestWebRequestCreate ());
			TestWebRequest request = TestWebRequestCreate.CreateTestRequest (json_response);

			OpenFDAQuery query = new OpenFDAQuery ();
			query.baseUrl = "test://testUrl";
			query.apiKey = "iECPoOgnA6LVBZ5X3PHyx5Slpj1smHE6u4FuzlHl";
			query.source = OpenFDAQuery.FDAReportSource.food;
			query.type = OpenFDAQuery.FDAReportType.enforcement;
			bool result = await query.RunQueryAsync();

			Assert.IsTrue (result);
			Assert.AreEqual ("F-0924-2013", query.response.results [1].recall_number);
			Assert.AreEqual ("US", query.response.results [0].country);
            */
		}
	}
}

