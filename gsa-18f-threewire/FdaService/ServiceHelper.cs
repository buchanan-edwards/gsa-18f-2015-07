using System;
using Newtonsoft.Json;

namespace FdaService
{
    public class ServiceHelper
    {
        public static string ApiKey = "40tqTRFnKf0bViGI2fkXF6kxZjQhqzY41UaeGa5D";
        public static string ApiKeyTemplate = "api_key={0}&";
        public static T GetData<T>(string baseUri, string endPoint, string parameters)
        {
            var apiKeyParam = String.Format(ApiKeyTemplate, ApiKey);
            var httpClient = new System.Net.Http.HttpClient();
            try {
                var data = httpClient.GetStringAsync(baseUri + endPoint + apiKeyParam + parameters).Result;

                return JsonConvert.DeserializeObject<T>(data);
            }
            catch (Exception ex)
            {
                return default(T);
            }
        }
    }
}
