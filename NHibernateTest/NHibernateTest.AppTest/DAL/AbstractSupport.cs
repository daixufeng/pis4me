using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NHibernate.App.Test.DAL
{
    public abstract class AbstractSupport
    {
       public readonly log4net.ILog log = log4net.LogManager.GetLogger(typeof(AbstractSupport));
    }
}
