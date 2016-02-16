using System.Collections.Generic;
using Newtonsoft.Json;

namespace FdaService.Models.Drug.Label
{
    public class Result
    {
        [JsonProperty("set_id")]
        public string setid { get; set; }
        public List<string> indications_and_usage { get; set; }
        public List<string> keep_out_of_reach_of_children { get; set; }
        public List<string> dosage_and_administration { get; set; }
        public List<string> purpose { get; set; }
        public string version { get; set; }
        public string id { get; set; }
        public List<string> package_label_principal_display_panel { get; set; }
        public List<string> active_ingredient { get; set; }
        public List<string> inactive_ingredient { get; set; }
        
        [JsonProperty("__invalid_name__@epoch")]
        public double __invalid_name__epoch { get; set; }
        public string effective_time { get; set; }
        public Openfda openfda { get; set; }
        public List<string> spl_product_data_elements { get; set; }
        public List<string> when_using { get; set; }
        public List<string> warnings { get; set; }

        public string time { get; set; }
        public int count { get; set; }
    }
}