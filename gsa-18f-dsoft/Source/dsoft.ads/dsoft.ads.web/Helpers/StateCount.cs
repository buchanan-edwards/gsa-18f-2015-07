using System;

namespace dsoft.ads.web.Models
{
	public class StateCount
	{
		public StateCount (int idx, string name, string abbr)
		{
			this.Index = idx;
			this.StateName = name;
			this.StateAbbr = abbr;
			this.Count = 0;
		}

		#region Properties
		public int Index { get; set; }
		public string StateName { get; set; }
		public string StateAbbr { get; set; }
		public int Count { get; set; }
		#endregion
	}
}

