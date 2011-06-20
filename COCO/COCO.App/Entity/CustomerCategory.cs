using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    public class CustomerCategory
    {
        public virtual int Id
        {
            get;
            set;
        }

        public virtual string Category
        {
            get;
            set;
        }
    }
}
