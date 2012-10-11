using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MyRabbit.Entity
{
    #region System Entities
    public partial class User
    {
        public String RoleName { get; set; }
    }

    public partial class OperationLogs
    {
        public String OperatorName { get; set; }
    }
    #endregion

}
