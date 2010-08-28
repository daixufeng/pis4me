using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;

namespace MyWork.DAL
{
    public class Connection
    {
        public static SqlConnection GetConnection() {
            string connString = System.Configuration.ConfigurationManager
                .ConnectionStrings["connectionstring"].ConnectionString;
            SqlConnection conn = new SqlConnection(connString);
            if (conn.State.Equals(ConnectionState.Closed)) {
                conn.Open();
            }
            return conn;
        }
    }
}
