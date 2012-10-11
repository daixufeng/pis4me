using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Objects.DataClasses;

namespace MyRabbit.Entity
{
    public class PageData<T>
    {
        /// <summary>
        /// Total Count
        /// </summary>
        public int Count { get; set; }

        /// <summary>
        /// 
        /// </summary>
        public List<T> Entities { get; set; }
    }
}
