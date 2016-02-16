using System.Collections.Generic;

namespace FdaService.Models.Device.Event
{
    public class RootObject
    {
        public Meta meta { get; set; }
        public List<Result> results { get; set; }
    }
}
