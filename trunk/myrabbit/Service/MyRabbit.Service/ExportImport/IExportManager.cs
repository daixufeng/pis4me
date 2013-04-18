using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using MyRabbit.Entity;

namespace MyRabbit.Service.ExportImport
{
    public interface IExportManager
    {
        void ExportUserToXlsx(Stream stream, IList<User> users);
    }
}
