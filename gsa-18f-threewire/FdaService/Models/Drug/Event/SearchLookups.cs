using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Reflection;

namespace FdaService.Models.Drug.Event
{
    public class SearchLookups
    {
        public static IEnumerable<SearchCriteria> GetResultFields()
        {
            var list = new List<SearchCriteria>();

            list.AddRange(GetResultFields<Result>(""));
            list.AddRange(GetResultFields<Patient>("patient."));
            list.AddRange(GetResultFields<Drug>("patient.drug."));
            list.AddRange(GetResultFields<Openfda>("patient.drug.openfda."));
            list.AddRange(GetResultFields<Reaction>("patient.reaction."));
            return list;
        }


        public static IEnumerable<SearchCriteria> GetResultFields<T>(string prefix)
        {
            var resultInfo = typeof(T);
            var fields = resultInfo.GetProperties().Where(f => f.PropertyType == typeof(string) || f.PropertyType==typeof(List<string>)).Select(f=> new SearchCriteria { FullName = prefix + f.Name, ShortName = f.Name });
            return fields;
        }
    }

    public class SearchCriteria
    {
        public string ShortName { get; set; }
        public string FullName { get; set; }
    }
}
