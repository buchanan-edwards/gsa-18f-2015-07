using System;

namespace dsoft.ads.web.Models
{
    public class RecallCount
    {
        public RecallCount(int year, int count)
        {
            this.Year = year;
            this.Count = count;
        }

        #region Properties
        public int Year { get; set; }
        public int Count { get; set; }
        #endregion
    }
}

