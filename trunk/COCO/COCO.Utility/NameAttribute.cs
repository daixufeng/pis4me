using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.Utility
{
    [System.AttributeUsage(System.AttributeTargets.All, AllowMultiple = false)]
    public class ShowNameAttribute : Attribute
    {
        protected string _StringName;
        public string StringName
        {
            get { return _StringName; }
            set { _StringName = value; }
        }

        public ShowNameAttribute(string strAttributeName)
        {
            _StringName = strAttributeName;
        }

        public ShowNameAttribute()
        {
            _StringName = "Not Set";
        }
    }
}
