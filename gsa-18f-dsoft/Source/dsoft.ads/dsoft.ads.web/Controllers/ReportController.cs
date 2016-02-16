using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using dsoft.ads.web.ViewModels;
using Newtonsoft.Json;

namespace dsoft.ads.web.Controllers
{
    public class ReportController : Controller
    {
        public ActionResult Index()
        {
            return View ();
        }

		public async Task<JsonResult> GeoReport(string keyword = null, DateTime? startDate = null, DateTime? endDate = null)
		{
			GeoReportViewModel geo = new GeoReportViewModel (false);
			await geo.GetGeoReport (true, keyword, startDate, endDate);

			return Json(geo, JsonRequestBehavior.AllowGet);
		}

        public async Task<JsonResult> FinancialReport(string keyword = null, string state = null, DateTime? startDate = null, DateTime? endDate = null)
		{
			FinancialReportViewModel fin = new FinancialReportViewModel (false);
            await fin.GetFinancialReport (true, keyword, state, startDate, endDate);

			return Json(fin, JsonRequestBehavior.AllowGet);
		}

        public async Task<JsonResult> BusinessReport(string keyword = null, string state = null)
        {
            BusinessReportViewModel bus = new BusinessReportViewModel(false);
            await bus.GetFinancialReport(true, keyword, state);

            return Json(bus, JsonRequestBehavior.AllowGet);
        }
    
    }
}
