using System;

namespace dsoft.ads.web.Models
{
	public class OpenFDAResponseMeta
	{
		public OpenFDAResponseMeta ()
		{
		}

		#region Properties
		public string disclaimer { get; set; }
		public string license { get; set; }
		public string last_updated { get; set; }
		public OpenFDAResponseMetaResults results { get; set; }
		#endregion
	}
}

