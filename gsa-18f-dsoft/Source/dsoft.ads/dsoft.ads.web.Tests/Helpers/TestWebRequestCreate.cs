using System;
using System.Net;

namespace dsoft.ads.web.Tests
{
	public class TestWebRequestCreate : IWebRequestCreate
	{
		static WebRequest nextRequest;
		static object lockObject = new object();

		static public WebRequest NextRequest
		{
			get { return nextRequest ;}
			set
			{
				lock (lockObject)
				{
					nextRequest = value;
				}
			}
		}

		public WebRequest Create(Uri uri)
		{
			return nextRequest;
		}

		public static TestWebRequest CreateTestRequest(string response)
		{
			TestWebRequest request = new TestWebRequest(response);
			NextRequest = request;
			return request;
		}
	}
}

