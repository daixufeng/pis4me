using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NHibernateTest.App.Model
{
    public class Department:Member
    {
        public virtual Employee Manager { get; set; }

        public virtual Department Parent { get; set; }

        public virtual DateTime CreateDate { get; set; }
    }
}
