using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    /// <summary>
    /// 会员套餐服务服务折扣率
    /// </summary>
    public class ServicePackagePrivilege
    {
        public virtual CustomerCategory Category
        {
            get;
            set;
        }

        public virtual ServicePackage ServicePackage
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
            if (obj is ServicePackagePrivilege)
            {
                return Category.Id.Equals(((CustomerCategory)obj).Id) &&
                    ServicePackage.Id.Equals(((ServicePackage)obj).Id);
            }
            return false;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
