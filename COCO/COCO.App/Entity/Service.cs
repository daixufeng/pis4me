using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    public class Service
    {
        public virtual int Id
        {
            get;
            set;
        }

        public virtual string ServiceName
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
