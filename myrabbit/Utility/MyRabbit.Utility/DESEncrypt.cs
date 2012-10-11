using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Security.Cryptography;
using System.IO;

namespace MyRabbit.Utility
{
    public  class DESEncrypt
    {
        private string _iv = "135798642";
        private string _key = "acfeffsd";
        private DES _des = new DESCryptoServiceProvider();
        private Encoding _encoding = Encoding.Unicode;

        /// <summary>
        /// 
        /// </summary>
        /// <value>The encrypty key.</value>
        public string EncryptyKey
        {
            set { _key = value; }
            get { return _key; }
        }

        /// <summary>
        /// 
        /// </summary>
        /// <value>The encoding mode.</value>
        public Encoding EncodingMode
        {
            set { _encoding = value; }
            get { return _encoding; }
        }

        /// <summary>
        /// Encrypt
        /// </summary>
        /// <param name="str">The STR.</param>
        /// <returns></returns>
        public string Encrypt(string str)
        {
            var ivb = Encoding.ASCII.GetBytes(_iv);
            var keyb = Encoding.ASCII.GetBytes(EncryptyKey);
            var tob = EncodingMode.GetBytes(str);
            byte[] encrypted;

            var encryptor = _des.CreateEncryptor(keyb, ivb);
            var msEncrypt = new MemoryStream();
            var csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write);
            csEncrypt.Write(tob, 0, tob.Length);
            csEncrypt.FlushFinalBlock();
            encrypted = msEncrypt.ToArray();
            csEncrypt.Close();
            msEncrypt.Close();
            return EncodingMode.GetString(encrypted);
        }

        /// <summary>
        /// Decript
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public string Decrypt(string str)
        {
            var ivb = Encoding.ASCII.GetBytes(_iv);
            var keyb = Encoding.ASCII.GetBytes(EncryptyKey);
            var tob = EncodingMode.GetBytes(str);
            byte[] encrypted;
            var encryptor = _des.CreateDecryptor(keyb, ivb);
            var msEncrypt = new MemoryStream();
            var csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write);
            csEncrypt.Write(tob, 0, tob.Length);
            csEncrypt.FlushFinalBlock();
            encrypted = msEncrypt.ToArray();
            csEncrypt.Close();
            msEncrypt.Close();
            return EncodingMode.GetString(encrypted);
        }
    }
}
