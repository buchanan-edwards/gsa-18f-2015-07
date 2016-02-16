using System;
using System.IO;
using System.Net;

namespace dsoft.ads.web.Tests
{
	public class TestWebResponse : WebResponse
	{
		public TestWebResponse (Stream responseStream)
		{
			this.responseStream = responseStream;
		}

		Stream responseStream;

		public override Stream GetResponseStream()
		{
			return responseStream;
		}
	}
}

