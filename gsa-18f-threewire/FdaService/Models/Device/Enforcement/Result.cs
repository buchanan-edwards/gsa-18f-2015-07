using Newtonsoft.Json;

namespace FdaService.Models.Device.Enforcement
{
    public class Result
    {
        public string recall_number { get; set; }
        public string reason_for_recall { get; set; }
        public string status { get; set; }
        public string distribution_pattern { get; set; }
        public string product_quantity { get; set; }
        public string recall_initiation_date { get; set; }
        public string state { get; set; }
        public string event_id { get; set; }
        public string product_type { get; set; }
        public string product_description { get; set; }
        public string country { get; set; }
        public string city { get; set; }
        public string recalling_firm { get; set; }
        public string report_date { get; set; }

        [JsonProperty("__invalid_name__@epoch")]
        public double __invalid_name__epoch { get; set; }
        public string voluntary_mandated { get; set; }
        public string classification { get; set; }
        public string code_info { get; set; }

        [JsonProperty("__invalid_name__@id")]
        public string __invalid_name__id { get; set; }
        public Openfda openfda { get; set; }
        public string initial_firm_notification { get; set; }

        public string time { get; set; }
        public string term { get; set; }
        public int count { get; set; }
    }
}