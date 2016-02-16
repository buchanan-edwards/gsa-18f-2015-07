using System;
using System.Linq;
using FdaService;
using FdaService.Models.Device.Enforcement;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace FdaServiceTests
{
    [TestClass]
    public class ServiceHelper_Device_EnforcementTests
    {
        [TestMethod]
        public void Device_Enforcement_SearchByReportDate()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/enforcement.json?",
                "count=report_date");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("First Item Time {0} and Count = {1}", results.results.FirstOrDefault().time, results.results.FirstOrDefault().count);
        }

        [TestMethod]
        public void Device_Enforcement_OneEnforcementReport()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/enforcement.json?",
                "search=report_date:[20040101+TO+20131231]&limit=1");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("RecallNumber {0} and Status = {1}", results.results.FirstOrDefault().recall_number, results.results.FirstOrDefault().status);
        }

        [TestMethod]
        public void Device_Enforcement_OneEnforcementReportOfHealthClassHazard()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/enforcement.json?",
                "search=classification:\"Class+III\"&limit=1");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("RecallNumber {0} and Status = {1}", results.results.FirstOrDefault().recall_number, results.results.FirstOrDefault().status);
        }

        [TestMethod]
        public void Device_Enforcement_CountOfVoluntaryVsMandatedReports()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/device/enforcement.json?",
                "count=voluntary_mandated.exact");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("Count = {0}, Term = {1}", results.results.FirstOrDefault().count, results.results.FirstOrDefault().term);
        }
    }
}
