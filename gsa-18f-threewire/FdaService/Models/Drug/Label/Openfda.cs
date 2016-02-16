using System.Collections.Generic;

namespace FdaService.Models.Drug.Label
{
    public class Openfda
    {
        public List<string> spl_id { get; set; }
        public List<string> product_ndc { get; set; }
        public List<bool> is_original_packager { get; set; }
        public List<string> route { get; set; }
        public List<string> substance_name { get; set; }
        public List<string> spl_set_id { get; set; }
        public List<string> package_ndc { get; set; }
        public List<string> product_type { get; set; }
        public List<string> generic_name { get; set; }
        public List<string> manufacturer_name { get; set; }
        public List<string> brand_name { get; set; }
        public List<string> application_number { get; set; }
    }
}