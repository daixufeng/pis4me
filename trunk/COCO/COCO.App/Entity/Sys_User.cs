using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace COCO.App.Entity
{
    public class Sys_User
    {
        public virtual int Id
        {
            get;
            set;
        }

        public virtual string UserName
        {
            get;
            set;
        }

        public virtual string Password
        {
            get;
            set;
        }

        public virtual string Role
        {
            get;
            set;
        }

        public virtual string LoginName
        {
            get;
            set;
        }

        public virtual string Mobile
        {
            get;
            set;
        }

        /// <summary>
        /// 身份证号码
        /// </summary>
        public virtual string IdentityCardNo
        {
            get;
            set;
        }
    }
}
