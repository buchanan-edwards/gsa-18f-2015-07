using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Web.Mvc;

namespace dsoft.ads.web.Helpers
{
	public class StateNames
	{
		public static readonly SortedDictionary<string, string> stateNames = new SortedDictionary<string, string>
		{
			{ "Alabama", "AL" },
			{ "Alaska", "AK" },
			{ "Arizona", "AZ" },
			{ "Arkansas", "AR" },
			{ "California", "CA" },
			{ "Colorado", "CO" },
			{ "Connecticut", "CT" },
			{ "Delaware", "DE" },
			{ "Florida", "FL" },
			{ "Georgia", "GA" },
			{ "Hawaii", "HI" },
			{ "Idaho", "ID" },
			{ "Illinois", "IL" },
			{ "Indiana", "IN" },
			{ "Iowa", "IA" },
			{ "Kansas", "KS" },
			{ "Kentucky", "KY" },
			{ "Louisiana", "LA" },
			{ "Maine", "ME" },
			{ "Maryland", "MD" },
			{ "Massachusetts", "MA" },
			{ "Michigan", "MI" },
			{ "Minnesota", "MN" },
			{ "Mississippi", "MS" },
			{ "Missouri", "MO" },
			{ "Montana", "MT" },
			{ "Nebraska", "NE" },
			{ "Nevada", "NV" },
			{ "New Hampshire", "NH" },
			{ "New Jersey", "NJ" },
			{ "New Mexico", "NM" },
			{ "New York", "NY" },
			{ "North Carolina", "NC" },
			{ "North Dakota", "ND" },
			{ "Ohio", "OH" },
			{ "Oklahoma", "OK" },
			{ "Oregon", "OR" },
			{ "Pennsylvania", "PA" },
			{ "Rhode Island", "RI" },
			{ "South Carolina", "SC" },
			{ "South Dakota", "SD" },
			{ "Tennessee", "TN" },
			{ "Texas", "TX" },
			{ "Utah", "UT" },
			{ "Vermont", "VT" },
			{ "Virginia", "VA" },
			{ "Washington", "WA" },
			{ "West Virginia", "WV" },
			{ "Wisconsin", "WI" },
			{ "Wyoming", "WY" }			
		};

		public StateNames ()
		{

		}

		public static List<string> GetStateList(string states)
		{
			List<string> statelist = new List<string> ();

			// TODO:  this match needs to be more robust - e.g. distinguish Washington state from D.C.
			foreach (KeyValuePair<string, string> kvp in stateNames) {
				if (states.Contains (kvp.Key) || states.Contains (kvp.Value) || (states.ToLower().Contains("nationwide")))
					statelist.Add (kvp.Value);
			}

			return statelist;
		}


        public static SelectList GetStateDropdown(string selectedState)
        {
            var states = stateNames
                .Select(x =>
                    new SelectListItem
                    {
                        Value = x.Key,
                        Text = x.Value
                    });

            List<SelectListItem> statelist = new List<SelectListItem>();
            statelist.AddRange(new SelectList(states, "Text", "Value"));
            statelist.Insert(0, new SelectListItem{Text = "", Value = ""});

            return new SelectList(statelist, "Value", "Text", selectedState);
        }
	}
}

               