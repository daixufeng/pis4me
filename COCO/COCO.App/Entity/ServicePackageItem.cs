using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    public class ServicePackageItem
    {
        public virtual ServicePackage ServicePackage
        {
            get;
            set;
        }

        public virtual Service Service
        {
            get;
            set;
        }

        public override bool Equals(object obj)
        {
            if (obj is ServicePackageItem)
            {
                return ServicePackage.Id.Equals(((ServicePackage)obj).Id) &&
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
