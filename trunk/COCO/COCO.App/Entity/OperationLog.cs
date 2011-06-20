using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    /// <summary>
    /// 存储操作日志
    /// </summary>
    public class OperationLog
    {
        public virtual int Id
        {
            get;
            set;
        }

        public virtual Sys_User Operator
        {
            get;
            set;
        }

        public virtual DateTime CreateDate
        {
            get;
            set;
        }

        public virtual string Operation
        {
            get;
            set;
        }
    }
}
