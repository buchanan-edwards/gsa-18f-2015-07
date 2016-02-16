using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using System.Web.Mvc.Ajax;
using dsoft.ads.web.Models;
using dsoft.ads.web.Helpers;
using dsoft.ads.web.ViewModels;

namespace dsoft.ads.web.Controllers
{
	public class HomeController : Controller
	{
		public ActionResult Index ()
		{
			return View ();
		}

		public ActionResult About()
		{
			return View ();
		}

        public ActionResult GeographicReport (string keyword, DateTime? startDate, DateTime? endDate)
		{
			ViewData ["HideStateFilter"] = true;
            return View(new BaseViewModel());
		}

        public ActionResult BusinessReport () 
		{
            ViewData["HideDateFilter"] = true;
            return View (new BaseViewModel());
		}

        public ActionResult FinancialReport ()
		{
            ViewData["HideDateFilter"] = true;
            return View (new BaseViewModel());
		}

        //public async Task<ActionResult> ReportList (string sortOrder, int? page, int? pageSize, string keyword = null, string state = null, DateTime? startDate = null, DateTime? endDate = null)
        public ActionResult ReportList (string sortOrder, int? page, int? pageSize, string keyword = null, string state = null, DateTime? startDate = null, DateTime? endDate = null)
		{
            string referrer = string.Empty;
            if (Request.UrlReferrer != null)
                referrer = Request.UrlReferrer.AbsoluteUri;

            ReportListViewModel report = new ReportListViewModel();
            var t = report.GetReportList(sortOrder, page, pageSize, keyword, state, startDate, endDate, referrer);
            t.Wait();

            return View(report);
		}

        public ActionResult ReportDetails (string id, string eventid)
		{
            ReportDetailsViewModel report = new ReportDetailsViewModel();
            var t = report.GetReportDetails(id, eventid);
            t.Wait();

            return View (report);
		}
	}
}

