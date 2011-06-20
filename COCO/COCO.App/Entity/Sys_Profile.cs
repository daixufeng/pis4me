using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    public class Sys_Profile
    {
        public virtual int Id
        {
            get;
            set;
        }

        public virtual string SettingKey
        {
            get;
            set;
        }

        public virtual string SettingValue
        {
            get;
            set;
        }

        public virtual string Remark
        {
            get;
            set;
        }
    }
}
