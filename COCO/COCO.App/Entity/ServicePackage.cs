using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    /// <summary>
    /// 服务套餐
    /// </summary>
    public class ServicePackage
    {
        public virtual int Id
        {
            get;
            set;
        }

        public virtual string PackageName
        {
            get;
            set;
        }

        public virtual decimal Price
        {
            get;
            set;
        }
    }
}
