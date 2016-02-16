using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using System.Web.Mvc.Ajax;
using dsoft.ads.web.Models;
using dsoft.ads.web.Helpers;
using PagedList;

namespace dsoft.ads.web.ViewModels
{
    public class ReportListViewModel : BaseViewModel
	{
        public const string FilterSessionKey = "ReportListFilters";

		public string CurrentSort { get; set; }	
		public string EventIdSortParm { get; set; }
		public string RecallNumSortParm { get; set; }
		public string ReasonSortParm { get; set; }
		public string StatusSortParm { get; set; }
		public int Count { get; set; }
		public int PageSize { get; set; }
		public PagedList.IPagedList<OpenFDAResult> Results { get; set; }
		public List<SelectListItem> PageSizeOptions { get; set; }

        public ReportListViewModel () {}

        public ReportListViewModel (bool setStates) : base(setStates) {}

        public async Task GetReportList(string sortOrder, int? page, int? pageSize, string keyword, string state, DateTime? start, DateTime? end, string referrer)
        {
            // restore filters on return from detail report
            if ((keyword == null) &&
                (state == null) &&
                (start == null) &&
                (end == null) &&
                (HttpContext.Current.Session[ReportListViewModel.FilterSessionKey] != null) &&
                (referrer.ToLower().Contains("/reportdetails/")))
            {
                List<Object> filters = (List<Object>)HttpContext.Current.Session[ReportListViewModel.FilterSessionKey];
                keyword = (string)filters[0];
                state = (string)filters[1];
                start = (DateTime?)filters[2];
                end = (DateTime?)filters[3];
            }
            else
            {
                this.SaveFilters(keyword, state, start, end);
            }

            this.SetFilters(false, keyword, state, start, end);

			this.CurrentSort = sortOrder;	
			this.EventIdSortParm = (sortOrder == "eventid" ? "eventid_desc" : "eventid");
			this.RecallNumSortParm = (sortOrder == "recallnum" ? "recallnum_desc" : "recallnum");
			this.ReasonSortParm = (sortOrder == "reason" ? "reason_desc" : "reason");
			this.StatusSortParm = (sortOrder == "status" ? "status_desc" : "status");

			// paging
			int displayPageSize = 10;
			int displayPageNumber = (page ?? 1);
			int sessionPageSize = SessionHelper.GetSessionInt("PubsPageSize");
			if (sessionPageSize != 0)
				displayPageSize = sessionPageSize;
			if (pageSize != null)
			{
				displayPageNumber = 1;
				Int32.TryParse(pageSize.ToString(), out displayPageSize);
			}
			this.PageSize = displayPageSize;
			SessionHelper.SetSession("PubsPageSize", displayPageSize);

			// paging options
			this.PageSizeOptions = new List<SelectListItem>();
			this.PageSizeOptions.Add(new SelectListItem { Text = "10", Value = "10", Selected = (displayPageSize == 10) });
			this.PageSizeOptions.Add(new SelectListItem { Text = "25", Value = "25", Selected = (displayPageSize == 25) });
			this.PageSizeOptions.Add(new SelectListItem { Text = "50", Value = "50", Selected = (displayPageSize == 50) });
			this.PageSizeOptions.Add(new SelectListItem { Text = "100", Value = "100", Selected = (displayPageSize == 100) });

			OpenFDAQuery query = new OpenFDAQuery ();
			query.source = OpenFDAQuery.FDAReportSource.food;
			query.type = OpenFDAQuery.FDAReportType.enforcement;
            query.queryLimit = 100;         // TODO: FDA API does not permit limit>100, would need to do a skip/take to get full dataset

            var searchQuery = new List<string>();

            if (!String.IsNullOrWhiteSpace (keyword))
                searchQuery.Add ( HttpUtility.UrlEncode(keyword));

            if (!String.IsNullOrWhiteSpace(state))
                searchQuery.Add(String.Format("state:{0}", HttpUtility.UrlEncode(state)));

            if ((start != null) && (end != null))
                searchQuery.Add (String.Format ("recall_initiation_date:[{0:yyyyMMdd}+TO+{1:yyyyMMdd}]", start.Value, end.Value));

            if (searchQuery.Count > 0)
                query.querySearch = string.Join ("+AND+", searchQuery);

			bool success = await query.RunQueryAsync();

			if (success)
				this.ErrorMsg = String.Empty;
			else
				this.ErrorMsg = "An error occurred executing the query.  Please try again.";

            if (query.response == null)
            {
                this.Count = 0;
                this.Results = null;
                this.ErrorMsg = String.Empty;
            }
            else
            {
                var results = query.response.results.AsQueryable();
                this.Count = results.Count();

                // sort
                switch (sortOrder)
                {
                    case "eventid":
                        results = results.OrderBy(r => r.event_id);
                        break;
                    case "eventid_desc":
                        results = results.OrderByDescending(r => r.event_id);
                        break;
                    case "recallnum":
                        results = results.OrderBy(r => r.recall_number);
                        break;
                    case "recallnum_desc":
                        results = results.OrderByDescending(r => r.recall_number);
                        break;
                    case "reason":
                        results = results.OrderBy(r => r.reason_for_recall);
                        break;
                    case "reason_desc":
                        results = results.OrderByDescending(r => r.reason_for_recall);
                        break;
                    case "status":
                        results = results.OrderBy(r => r.status);
                        break;
                    case "status_desc":
                        results = results.OrderByDescending(r => r.status);
                        break;
                }

                this.Results = results.ToPagedList (displayPageNumber, displayPageSize);
            }

			if (displayPageSize == -1)
                displayPageSize = this.Count;
		}

        private void SaveFilters(string keyword, string state, DateTime? start, DateTime? end)
        {
            /*
             * TODO:  this is a basic implementation to restore filter settings when the user returns from detail report.
             * This does not account for many factors like having multiple reports open in tabs, etc.  Need a more thorough 
             * design, but do not want to convert all detail report links and return link to posts (because isn't RESTful)
             * or include all the filters on the querystring.
             */
            HttpContext.Current.Session[FilterSessionKey] = new List<Object>() {keyword, state, start, end};
        }
	}
}

