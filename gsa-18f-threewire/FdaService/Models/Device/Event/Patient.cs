using System.Collections.Generic;

namespace FdaService.Models.Device.Event
{
    public class Patient
    {
        public string date_received { get; set; }
        public string patient_sequence_number { get; set; }
        public string sequence_number_treatment { get; set; }
        public List<string> sequence_number_outcome { get; set; }
    }
}