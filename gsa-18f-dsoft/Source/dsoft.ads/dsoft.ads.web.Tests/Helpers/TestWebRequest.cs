using System;
using System.IO;
using System.Net;

namespace dsoft.ads.web.Tests
{
	public class TestWebRequest : WebRequest
	{
		public TestWebRequest (string response)
		{
			responseStream = new MemoryStream(System.Text.Encoding.UTF8.GetBytes(response));
		}

		MemoryStream requestStream = new MemoryStream();
		MemoryStream responseStream;

		public override string Method { get; set; }
		public override string ContentType { get; set; }
		public override long ContentLength { get; set; }

		public string ContentAsString()
		{
			return System.Text.Encoding.UTF8.GetString(requestStream.ToArray());
		}

		public override Stream GetRequestStream()
		{
			return requestStream;
		}

		public override WebResponse GetResponse()
		{
			return new TestWebResponse(responseStream);
		}
	}
}

