using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using System.Web.Script.Serialization;
using dsoft.ads.web;
using dsoft.ads.web.Controllers;
using dsoft.ads.web.Models;
using dsoft.ads.web.ViewModels;
using Newtonsoft.Json;

namespace dsoft.ads.web.Tests
{
	[TestFixture]
	public class ReportControllerTest
	{
        /*
         * TODO:  tests in this class run queries against the live FDA API.
         * The queries should be mocked as in OpenFDAQueryTest.cs to avoid live calls.
         * This will require mocking the multiple API calls made by the ViewModel logic.
         * 
         */

		[Test]
		public async void GeoReport ()
		{
			var controller = new ReportController ();
            var result = await controller.GeoReport ();

			GeoReportViewModel geo = null;
			try 
            {
                geo = (GeoReportViewModel)result.Data;
			} 
            catch (Exception ex) 
            {
				throw new Exception ("GeoReportViewModel failed to parse: " + ex.Message);
			}

			Assert.NotNull (geo);
			Assert.AreEqual (String.Empty, geo.ErrorMsg);
			Assert.AreEqual (50, geo.data.Count);
		}

		[Test]
		public async void FinancialReport()
		{
			var controller = new ReportController ();
			var result = await controller.FinancialReport ();

			FinancialReportViewModel fin = null;
			try 
            {
                fin = (FinancialReportViewModel)result.Data;
			} 
            catch (Exception ex) 
            {
				throw new Exception ("FinancialReportViewModel failed to parse: " + ex.Message);
			}

			Assert.NotNull (fin);
			Assert.AreEqual (String.Empty, fin.ErrorMsg);
			Assert.AreEqual (10, fin.data.Count);
		}

        [Test]
        public async void BusinessReport()
        {
            var controller = new ReportController ();
            var result = await controller.BusinessReport ();

            BusinessReportViewModel bus = null;
            try 
            {
                bus = (BusinessReportViewModel)result.Data;
            } 
            catch (Exception ex) 
            {
                throw new Exception ("BusinessReportViewModel failed to parse: " + ex.Message);
            }

            Assert.NotNull (bus);
            Assert.AreEqual (String.Empty, bus.ErrorMsg);

            int span = DateTime.Today.Year - 2008 + 1;
            Assert.AreEqual (span, bus.data.Count);
        }
    
    }
}

