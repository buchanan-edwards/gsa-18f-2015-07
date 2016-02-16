using System.Collections.Generic;

namespace FdaService.Models.Drug.Event
{
    public class Patient
    {
        public List<Reaction> reaction { get; set; }
        public string patientonsetage { get; set; }
        public string patientonsetageunit { get; set; }
        public List<Drug> drug { get; set; }
        public string patientsex { get; set; }
    }
}