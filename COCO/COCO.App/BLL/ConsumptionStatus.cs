using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using COCO.Utility;

namespace COCO.App.BLL
{
    public enum ConsumptionStatus
    {
        /// <summary>
        /// 进行中
        /// </summary>
        [ShowName("Processing")]
        Processing = 1,
        /// <summary>
        /// 完成
        /// </summary>
        [ShowName("Completed")]
        Completed = 2,
        /// <summary>
        /// 取消掉了
        /// </summary>
        [ShowName("Cancelled")]
        Cancelled = 3,
    }
}
