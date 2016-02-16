using System;
using System.Collections.Generic;

namespace dsoft.ads.web.Models
{
	public class CompanyCount
	{
		public CompanyCount (string name, int count)
		{
			this.Name = name;
			this.TotalCount = count;
			this.YearlyCounts = new Dictionary<string, int> ();
			this.CountNonZero = 0;
		}

		#region Properties
		public string Name { get; set; }
		public int TotalCount { get; set; }
		public int CountNonZero { get; set; }
		public Dictionary<string, int> YearlyCounts { get; set; }
		#endregion
	}
}

