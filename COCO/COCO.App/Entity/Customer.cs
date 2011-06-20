using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    public class Customer
    {
        public virtual int Id
        {
            get;
            set;
        }

        /// <summary>
        /// 会员卡号
        /// </summary>
        public virtual string CardNo
        {
            get;
            set;
        }

        /// <summary>
        /// 姓名
        /// </summary>
        public virtual string CustomerName
        {
            get;
            set;
        }

        public virtual string Mobile
        {
            get;
            set;
        }

        public virtual string Telphone
        {
            get;
            set;
        }

        public virtual string Email
        {
            get;
            set;
        }

        /// <summary>
        /// 生日
        /// </summary>
        public virtual DateTime Birthday
        {
            get;
            set;
        }

        /// <summary>
        /// 会员类别
        /// </summary>
        public virtual CustomerCategory Category
        {
            get;
            set;
        }

        /// <summary>
        /// 累计消费
        /// </summary>
        public virtual int TotalConsumption
        {
            get;
            set;
        }

        /// <summary>
        /// 余额
        /// </summary>
        public virtual int Balance
        {
            get;
            set;
        }

        /// <summary>
        /// 消费次数
        /// </summary>
        public virtual int ConsumptionCount
        {
            get;
            set;
        }
    }
}
