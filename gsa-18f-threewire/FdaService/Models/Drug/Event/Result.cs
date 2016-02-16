using Newtonsoft.Json;

namespace FdaService.Models.Drug.Event
{
    public class Result
    {
        public string safetyreportid { get; set; }
        public string fulfillexpeditecriteria { get; set; }
        public object receiver { get; set; }
        public string receivedateformat { get; set; }
        public string receiptdateformat { get; set; }
        public object primarysource { get; set; }
        public string receivedate { get; set; }
        public string seriousnessother { get; set; }
        public Sender sender { get; set; }

        [JsonProperty("__invalid_name__@epoch")]
        public double __invalid_name__epoch { get; set; }
        public Patient patient { get; set; }
        public string receiptdate { get; set; }
        public string transmissiondate { get; set; }
        public string transmissiondateformat { get; set; }
        public string seriousnesshospitalization { get; set; }
        public string serious { get; set; }
        public string companynumb { get; set; }

        public string term { get; set; }
        public string time { get; set; }
        public int count { get; set; }
    }
}