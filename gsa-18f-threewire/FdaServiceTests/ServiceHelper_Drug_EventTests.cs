using System;
using System.Linq;
using FdaService;
using FdaService.Models.Drug.Event;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace FdaServiceTests
{
    [TestClass]
    public class ServiceHelper_Drug_EventTests
    {
        [TestMethod]
        public void Drug_Event_SearchByReceiveDate()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/event.json?",
                "search=receivedate:[20040101+TO+20150101]&count=receivedate");

            Assert.IsNotNull(results.meta);
        }

        [TestMethod]
        public void Drug_Event_SearchByPharmacologicClass()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/event.json?",
                "search=patient.drug.openfda.pharm_class_epc:\"nonsteroidal+anti-inflammatory+drug\"&limit=1");

            Assert.IsNotNull(results.meta);
            System.Diagnostics.Debug.WriteLine("Total Lines = {0}", results.meta.results.total);
        }

        [TestMethod]
        public void Drug_Event_GetCountOfPatientReactions()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/event.json?",
                "search=patient.drug.openfda.pharm_class_epc:\"nonsteroidal+anti-inflammatory+drug\"&count=patient.reaction.reactionmeddrapt.exact");

            Assert.IsNotNull(results.results);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("First Item Count = {0}", results.results.FirstOrDefault().count);
        }
    }
}
