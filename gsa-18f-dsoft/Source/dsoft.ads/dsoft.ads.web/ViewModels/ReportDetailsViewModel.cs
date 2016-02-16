using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using dsoft.ads.web.Models;

namespace dsoft.ads.web.ViewModels
{
    public class ReportDetailsViewModel
	{
		public OpenFDAResult Result { get; set; }

        public ReportDetailsViewModel () {}

        public async Task GetReportDetails(string id, string eventid)
        {
            OpenFDAQuery query = new OpenFDAQuery ();
            query.source = OpenFDAQuery.FDAReportSource.food;
            query.type = OpenFDAQuery.FDAReportType.enforcement;
            query.querySearch = String.Format("event_id:{0}", HttpUtility.HtmlEncode(eventid));
            query.queryLimit = 100;
            bool result = await query.RunQueryAsync();
            if (result) {
                this.Result = query.response.results.Where (r => r.id.Equals(id)).FirstOrDefault ();
            }
        }
	}
}

