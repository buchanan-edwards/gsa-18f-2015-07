using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using dsoft.ads.web.Helpers;
using dsoft.ads.web.Models;

namespace dsoft.ads.web.ViewModels
{
    public class FinancialReportViewModel : BaseViewModel
	{
		public List<CompanyCount> data { get; set; }

        public FinancialReportViewModel () {}

        public FinancialReportViewModel (bool setStates) : base(setStates) {}

        public async Task GetFinancialReport(bool isAjax, string keyword, string state, DateTime? start, DateTime? end)
		{
			this.data = new List<CompanyCount> ();
            this.SetFilters(isAjax, keyword, state, start, end);

			OpenFDAQuery query = new OpenFDAQuery ();
			query.source = OpenFDAQuery.FDAReportSource.food;
			query.type = OpenFDAQuery.FDAReportType.enforcement;
			query.queryCount = "recalling_firm.exact";

            var searchQuery = new List<string>();

            if (!String.IsNullOrWhiteSpace (keyword))
                searchQuery.Add ( HttpUtility.UrlEncode(keyword));

            if (!String.IsNullOrWhiteSpace(state))
                searchQuery.Add(String.Format("state:{0}", HttpUtility.UrlEncode(state)));

            if ((start != null) && (end != null))
                searchQuery.Add (String.Format ("recall_initiation_date:[{0:yyyyMMdd}+TO+{1:yyyyMMdd}]", start.Value, end.Value));
            else 
                searchQuery.Add (String.Format ("recall_initiation_date:[20080101+TO+{0}0101]", DateTime.Today.Year+1));
			
            if (searchQuery.Count > 0)
                query.querySearch = string.Join ("+AND+", searchQuery);

            bool success = await query.RunQueryAsync();

			if (!success)
				this.ErrorMsg = "An error occurred executing the query.  Please try again.";
			else 
            {
				this.ErrorMsg = String.Empty;

				// build top company list
                int resultLimit = 0;
                foreach (OpenFDAResult result in query.response.results)
                {

                    string name = result.term;

                    /*
					 * TODO:  FDA API contains a known issue which does not permit string text search queries 
                     * containing single-quote whether or not it is URL-encoded properly.  For demonstration 
                     * purposes, names with single-quote/apostrophes will be excluded from the top-ten list.
                     * Actual fix for this issue may involve making broader queries and string matching to find 
                     * the desired company within the result set.
					 * 
					 */
                    if (!name.Contains("'"))
                    {
                        resultLimit++;
                        if (resultLimit > 10)
                            break;

                        this.data.Add(new CompanyCount(name, 0));
                    }
                }

                // build company counts per year
                int loopStart = 2008;
                int loopEnd = DateTime.Today.Year;
                if ((start != null) && (end != null))
                {
                    loopStart = ((DateTime)start).Year;
                    loopEnd = ((DateTime)end).Year;
                }

                bool yearHasHadData = false;
                for (int yr = loopStart; yr <= loopEnd; yr++)
                {
                    bool yearHasData = false;
                    OpenFDAQuery subquery = new OpenFDAQuery();
                    subquery.source = OpenFDAQuery.FDAReportSource.food;
                    subquery.type = OpenFDAQuery.FDAReportType.enforcement;
                    subquery.queryCount = "recalling_firm.exact";
                    subquery.querySearch = String.Format("recall_initiation_date:[{0}0101+TO+{1}0101]", yr, yr + 1);
                    subquery.queryLimit = 1000;
                    success = await subquery.RunQueryAsync();

                    foreach (CompanyCount company in this.data)
                    {
                        int cnt = 0;
                        if ((success) && (subquery.response != null) && (subquery.response.results.Count > 0))
                        {
                            var result = subquery.response.results.Where(r => r.term.Equals(company.Name)).FirstOrDefault();
                            if (result != null)
                            {
                                cnt = result.countNumeric;
                                yearHasData = true;
                                yearHasHadData = true;
                            }
                        }
                        company.YearlyCounts.Add(yr.ToString(), cnt);
                    }

                    if (!yearHasData && !yearHasHadData)
                    {
                        // remove year from all company dictionaries if year (and no previous year) contains data
                        foreach (CompanyCount company in this.data)
                        {
                            company.YearlyCounts.Remove(yr.ToString());
                        }
                    }
                }

            }
		}

	}
}

