using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using Common.Logging;

namespace MyRabbit.ServiceTest
{
    [TestFixture]
    public class TestSupport
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


        public void LogTest()
        {
            Log.Debug("Debug");
            Log.Error("Error");
        }
    }
}
