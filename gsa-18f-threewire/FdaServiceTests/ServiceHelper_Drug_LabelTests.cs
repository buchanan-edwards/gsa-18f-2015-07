using System;
using System.Linq;
using FdaService;
using FdaService.Models.Drug.Label;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace FdaServiceTests
{
    [TestClass]
    public class ServiceHelper_Drug_LabelTests
    {
        [TestMethod]
        public void Drug_Label_SearchByEffectiveTime()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/label.json?",
                "search=effective_time:[20090601+TO+20140731]&count=effective_time");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("First Item Count = {0}", results.results.FirstOrDefault().count);
        }

        [TestMethod]
        public void Drug_Label_SearchByEffectiveTimeWithLimit1()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/label.json?",
                "search=effective_time:[20110601+TO+20121231]&limit=1");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("First Item Count = {0}", results.meta.results.total);
        }

        [TestMethod]
        public void Drug_Label_SearchRecordWithBoxWarning()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/label.json?",
                "search=_exists_:boxed_warning");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("Meta Results Total = {0}", results.meta.results.total);
        }

        [TestMethod]
        public void Drug_Label_CountOfLabelingByProductType()
        {
            var results = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/label.json?",
                "count=openfda.product_type.exact");

            Assert.IsNotNull(results.meta);
            Assert.IsTrue(results.results.Count > 0);
            System.Diagnostics.Debug.WriteLine("Results Count = {0}", results.results.FirstOrDefault().count);
        }
    }
}
