using System;

namespace dsoft.ads.web.Models
{
	public class OpenFDAResponseMetaResults
	{
		public OpenFDAResponseMetaResults ()
		{
		}

		#region Properties
		public int skip { get; set; }
		public int limit { get; set; }
		public int total { get; set; }
		#endregion
	}
}

