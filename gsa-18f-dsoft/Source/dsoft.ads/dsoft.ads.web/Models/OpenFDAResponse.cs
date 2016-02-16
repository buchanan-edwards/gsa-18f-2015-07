using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using dsoft.ads.web.Helpers;

namespace dsoft.ads.web.Models
{
	public class OpenFDAResponse
	{
		public OpenFDAResponse ()
		{
			results = new List<OpenFDAResult> ();
		}

		#region Properties
		public OpenFDAResponseMeta meta { get; set; }
		public List<OpenFDAResult> results {get; set;}
		public int count 
		{ 
			get {
				if (this.results != null)
					return this.results.Count;
				else
					return 0;
			}
		}
		#endregion
	}
}

