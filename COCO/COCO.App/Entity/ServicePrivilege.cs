using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    /// <summary>
    /// 会员服务折扣率
    /// </summary>
    public class ServicePrivilege
    {
        public virtual CustomerCategory Category
        {
            get;
            set;
        }

        public virtual Service Service
        {
            get;
            set;
        }

        public virtual int PrivilegeRate
        {
            get;
            set;
        }

        public override bool Equals(object obj)
        {
            if (obj is ServicePrivilege)
            {
                return Category.Id.Equals(((CustomerCategory)obj).Id) &&
                    Service.Id.Equals(((Service)obj).Id);
            }
            return false;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
