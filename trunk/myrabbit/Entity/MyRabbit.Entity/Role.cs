using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel.DataAnnotations.Schema;

namespace MyRabbit.Entity
{
    [Table("sm_role")]
    public class Role
    {
        public Int32 Id { get; set; }

        public String Name { get; set; }
    }
}
