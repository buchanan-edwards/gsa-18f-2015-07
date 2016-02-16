using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using dsoft.ads.web.Helpers;
using Newtonsoft.Json;

namespace dsoft.ads.web.Models
{
	public class OpenFDAResult
	{
		public OpenFDAResult ()
		{
		}

		#region Properties

		// Counts

		public string term { get; set; }

		public string count { get; set; }

		public int countNumeric {
			get {
				int ret = 0;
				Int32.TryParse (this.count, out ret);
				return ret;
			}
		}
		// Enforcement Reports
		[JsonProperty(PropertyName="@id")]
		[Display(Name = "ID", Description = "")]
		public string id { get; set; }

		[Display(Name = "Event ID", Description = "")]
		public int event_id { get; set; }

		[Display(Name = "Recall Number", Description = "The recall # for the event")]
		public string recall_number { get; set; }

		[Display(Name = "Report Date", Description = "")]
		public string report_date { get; set; }

		[Display(Name = "Recall Initiation Date", Description = "The date the recall began")]
		public string recall_initiation_date { get; set; }

		[Display(Name = "Classification", Description = "The classification of the recall. It may be Class I, Class II or Class III")]
		public string classification { get; set; }

		[Display(Name = "Voluntary/Mandated", Description = "How the recall was mandated")]
		public string voluntary_mandated { get; set; }

		[Display(Name = "Reason", Description = "The reason for the recall")]
		public string reason_for_recall { get; set; }

		[Display(Name = "Status", Description = "The status of the recall. It may be ongoing, complete or terminated")]
		public string status { get; set; }

		[Display(Name = "Recalling Firm", Description = "")]
		public string recalling_firm { get; set; }

		[Display(Name = "City", Description = "")]
		public string city { get; set; }

		[Display(Name = "State", Description = "")]
		public string state { get; set; }

		[Display(Name = "Country", Description = "")]
		public string country { get; set; }

		[Display(Name = "Dist. Pattern", Description = "The locations that the recall affects")]
		public string distribution_pattern { get; set; }

		[Display(Name = "Product Type", Description = "")]
		public string product_type { get; set; }

		[Display(Name = "Product Qty", Description = "The number of products being recalled")]
		public string product_quantity { get; set; }

		[Display(Name = "Product Desc", Description = "The description of the product being recalled")]
		public string product_description { get; set; }

		[Display(Name = "Code Info", Description = "Identifying factors of the affected product")]
		public string code_info { get; set; }

		[Display(Name = "Initial Notification", Description = "")]
		public string initial_firm_notification { get; set; }



		[Display(Name = "Report Date", Description = "")]
		public DateTime report_date_full { get; set; }

		[Display(Name = "Recall Initiation Date", Description = "The date the recall began")]
		public DateTime recall_initiation_date_full { get; set; }

		#endregion

	}
}

