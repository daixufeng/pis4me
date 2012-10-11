using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Utility;

namespace MyRabbit.Entity
{
    public enum OperationLogType
    {

        [ShowName("All")]
        All = 1,

        [ShowName("新增")]
        Insert = 2,

        [ShowName("修改")]
        Update = 3,

        [ShowName("删除")]
        Delete = 4,
    }
}
