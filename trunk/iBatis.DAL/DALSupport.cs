using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using IBatisNet.DataMapper;

namespace iBatis.DAL
{
    public abstract class DALSupport
    {
        public ISqlMapper Session
        {
            get { return Mapper.Instance(); }
        }
    }
}
