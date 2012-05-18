using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace iBatis.Factory
{
    public class DALFactory
    {
        public static T GetObject<T>(string objectName)
        {
            return (T) Assembly.Load("iBatis.DAL").CreateInstance("iBatis.DAL." + objectName);
        }
    }
}
