using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NHibernateTest.App.Model
{
    public class Employee:Member
    {
        public virtual string NickName { get; set; }

        public virtual Department Department { get; set; }

        public virtual string Email { get; set; }
    }
}
