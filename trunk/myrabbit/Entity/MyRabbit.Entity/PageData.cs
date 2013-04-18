using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MyRabbit.Entity
{
    public class PageData<T> where T : class
    {
        public Int32 PageNo { get; set; }

        public Int32 PageSize { get; set; }

        /// <summary>
        /// Total Count
        /// </summary>
        public int TotalCount { get; set; }

        /// <summary>
        /// 
        /// </summary>
        public IList<T> Records { get; set; }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="records"></param>
        /// <param name="totalCount"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        public PageData(IList<T> records, Int32 totalCount, Int32 pageNo, Int32 pageSize)
        {
            this.PageNo = pageNo;
            this.PageSize = pageSize;
            this.Records = records;
            this.TotalCount = totalCount;
        }
    }
}
