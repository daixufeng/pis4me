using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using log4net;

[assembly: log4net.Config.XmlConfigurator(Watch = true)]
namespace COCO.AppTest
{
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
       
    }
}
