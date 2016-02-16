using System;
using System.Linq;
using System.Globalization;
using System.Text;
using System.Web;
using System.Web.Mvc;
using dsoft.ads.web.Helpers;

namespace dsoft.ads.web.ViewModels
{
    public class BaseViewModel
    {
        public bool isAjax;
        public string ErrorMsg {get; set; }
        public string Subtitle { get; set; }
        public SelectList StateList { get; set; }
        public string FilterKeyword { get; set; }
        public string FilterState { get; set; }
        public string FilterStartDate { get; set; }
        public string FilterEndDate { get; set; }

        public BaseViewModel(bool setStates = true)
        {
            if (setStates)
                this.StateList = StateNames.GetStateDropdown(String.Empty);
        }

        protected void SetFilters(bool isAjax, string keyword = null, string state = null, DateTime? startDate = null, DateTime? endDate = null)
        {
            if (!isAjax)
                this.StateList = StateNames.GetStateDropdown(state);

            this.FilterKeyword = keyword;
            this.FilterState = state;
            this.FilterStartDate = (startDate != null) ? ((DateTime)startDate).ToShortDateString() : String.Empty;
            this.FilterEndDate = (endDate != null) ? ((DateTime)endDate).ToShortDateString() : String.Empty;

            StringBuilder subtitle = new StringBuilder();

            if (!String.IsNullOrEmpty(keyword))
                subtitle.Append(String.Format("Keyword: {0}; ", HttpUtility.HtmlEncode(keyword)));

            if ((startDate != null) && (endDate != null))
            {
                DateTimeFormatInfo dtfi = CultureInfo.CreateSpecificCulture("en-US").DateTimeFormat;
                subtitle.Append(String.Format("Date Range: {0} - {1}; ", ((DateTime)startDate).ToString("d", dtfi), ((DateTime)endDate).ToString("d", dtfi)));
            }

            char[] charsToTrim = {';', ' '};
            this.Subtitle = subtitle.ToString().TrimEnd(charsToTrim);
        }

    }
}

