using System;
using System.Web;

namespace dsoft.ads.web.Helpers
{
	public class SessionHelper
	{
		public static string GetSessionString(string key)
		{
			if (HttpContext.Current.Session[key] != null)
			{
				return HttpContext.Current.Session[key].ToString();
			}
			return String.Empty;
		}

		public static int GetSessionInt(string key)
		{
			int ret = 0;
			Int32.TryParse(GetSessionString(key), out ret);
			return ret;
		}

		public static void SetSession(string key, string value)
		{
			HttpContext.Current.Session[key] = value;
		}

		public static void SetSession(string key, int value)
		{
			SetSession(key, value.ToString());
		}
	}
}

