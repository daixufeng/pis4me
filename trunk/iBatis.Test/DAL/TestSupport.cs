using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using log4net;

namespace iBatis.Test.DAL
{
    class TestSupport
    {
        private ILog _log = null;
        public TestSupport()
        {
            _log = LogManager.GetLogger(this.GetType());
        }


        public ILog Log
        {
            get
            {
                if (_log == null) _log = LogManager.GetLogger(this.GetType());
                return _log;
            }
        }
    }
}
