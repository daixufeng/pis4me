using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    public class ConsumptionDetail
    {
        public virtual int Id
        {
            get;
            set;
        }

        /// <summary>
        /// 客户
        /// </summary>
        public virtual Customer Customer
        {
            get;
            set;
        }

        /// <summary>
        /// 收银员
        /// </summary>
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

        public virtual decimal Cost
        {
            get;
            set;
        }

        /// <summary>
        /// 服务人员
        /// </summary>
        public virtual Sys_User Waiter
        {
            get;
            set;
        }

        public virtual Service Service
        {
            get;
            set;
        }

        public virtual COCO.App.BLL.ConsumptionStatus Status
        {
            get;
            set;
        }
    }
}
