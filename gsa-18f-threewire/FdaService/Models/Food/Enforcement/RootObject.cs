using System.Collections.Generic;

namespace FdaService.Models.Food.Enforcement
{
    public class RootObject
    {
        public Meta meta { get; set; }
        public List<Result> results { get; set; }
    }
}
