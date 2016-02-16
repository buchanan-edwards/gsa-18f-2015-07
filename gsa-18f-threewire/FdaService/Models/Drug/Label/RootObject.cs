using System.Collections.Generic;

namespace FdaService.Models.Drug.Label
{
    public class RootObject
{
    public Meta meta { get; set; }
    public List<Result> results { get; set; }
}
}
