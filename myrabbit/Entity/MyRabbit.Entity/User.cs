using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MyRabbit.Entity
{
    [Serializable]
    [Table("sm_user")]
    public class User
    {
        [Key]
        public Int32 Id { get; set; }

        [Required(ErrorMessage="LoginName is Mandatory")]
        public String LoginName { get; set; }

        public String Email { get; set; }

        public String NickName { get; set; }

        public String Password { get; set; }

        public Nullable<DateTime> CreateTime { get; set; }

        public Nullable<Int32> IsLogon { get; set; }
    }
}
