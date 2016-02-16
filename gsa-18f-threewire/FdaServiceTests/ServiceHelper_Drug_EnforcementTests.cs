using System;
using System.Linq;
using FdaService;
using FdaService.Models.Drug.Enforcement;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace FdaServiceTests
{
    [TestClass]
    public class ServiceHelper_Drug_EnforcementTests
    {
        [TestMethod]
        public void Drug_Enforcement_CountByReportDate()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/enforcement.json?",
                "&count=report_date");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            //System.Diagnostics.Debug.WriteLine("First Item Count = {0}", results.results.FirstOrDefault().count);
        }

        [TestMethod]
        public void Drug_Enforcement_ByReportDateLimit1()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/enforcement.json?",
                "search=report_date:[20040101+TO+20131231]&limit=1");

            Assert.IsNotNull(results.meta);
            
            System.Diagnostics.Debug.WriteLine("Recall Number = {0}", results.results.FirstOrDefault().recall_number);
        }


        [TestMethod]
        public void Drug_Enforcement_ReportOnHealthHazardClassLimit1()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/enforcement.json?",
                "search=classification:\"Class+III\"&limit=1");

            Assert.IsNotNull(results.meta);

            System.Diagnostics.Debug.WriteLine("Recall Number = {0}", results.results.FirstOrDefault().recall_number);
        }


        [TestMethod]
        public void Drug_Enforcement_CountOfMandatoryVsMandated()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/enforcement.json?",
                "count=voluntary_mandated.exact");

            Assert.IsNotNull(results.meta);

            System.Diagnostics.Debug.WriteLine("Count = {0}, Term = {1}", results.results.FirstOrDefault().count, results.results.FirstOrDefault().term);
        }
    }
}
