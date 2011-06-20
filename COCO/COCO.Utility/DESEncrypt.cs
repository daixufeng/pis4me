using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Security.Cryptography;
using System.IO;

namespace COCO.Utility
{
    public  class DESEncrypt
    {
        private string _iv = "12345678";
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

        //private byte[] decodeBytes(string strBuffer)
        //{
        //    byte[] buffer = new byte[strBuffer.Length / 2];
        //    for (int i = 0; i < strBuffer.Length; i += 2)
        //    {
        //        int num2;
        //        try
        //        {
        //            num2 = byte.Parse(strBuffer.Substring(i, 2), NumberStyles.HexNumber);
        //        }
        //        catch (Exception)
        //        {
        //            num2 = 0;
        //        }
        //        buffer[i / 2] = (byte)(num2 + 0x80);
        //    }
        //    return buffer;
        //}
    }
}
