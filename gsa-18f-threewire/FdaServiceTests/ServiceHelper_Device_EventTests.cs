using System;
using System.Linq;
using FdaService;
using FdaService.Models.Device.Event;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace FdaServiceTests
{
    [TestClass]
    public class ServiceHelper_Device_EventTests
    {
        [TestMethod]
        public void Device_Event_SearchByDateReceived()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/event.json?",
                "search=date_received:[19910101+TO+20150101]&count=date_received");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("First Item Time {0} and Count = {1}", results.results.FirstOrDefault().time, results.results.FirstOrDefault().count);
        }

        [TestMethod]
        public void Device_Event_OneAdverseEventReport()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/event.json?",
                "search=date_received:[20130101+TO+20141231]&limit=1");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("ReportNumber {0} and Event Type = {1}", results.results.FirstOrDefault().report_number, results.results.FirstOrDefault().event_type);
        }

        [TestMethod]
        public void Device_Event_OneReportInvolvingAnXRay()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/event.json?",
                "search=device.generic_name:x-ray&limit=1");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("ReportNumber {0} and Event Type = {1}", results.results.FirstOrDefault().report_number, results.results.FirstOrDefault().event_type);
        }

        [TestMethod]
        public void Device_Event_CountOfTopEventTypesWithXRay()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/event.json?",
                "search=device.generic_name:x-ray&count=event_type.exact");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("Count = {0}, Term = {1}", results.results.FirstOrDefault().count, results.results.FirstOrDefault().term);
        }
    }
}
