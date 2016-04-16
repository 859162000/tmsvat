package com.deloitte.tms.pl.core.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileItem
{
  private String fileName;
  private String mimeType;
  private byte[] content;
  private File file;

  public FileItem(File file)
  {
    this.file = file;
  }

  public FileItem(String filePath)
  {
    this(new File(filePath));
  }

  public FileItem(String fileName, byte[] content)
  {
    this.fileName = fileName;
    this.content = content;
  }

  public FileItem(String fileName, byte[] content, String mimeType)
  {
    this(fileName, content);
    this.mimeType = mimeType;
  }

  public String getFileName() {
    if ((this.fileName == null) && (this.file != null) && (this.file.exists())) {
      this.fileName = this.file.getName();
    }
    return this.fileName;
  }

  public String getMimeType() throws IOException {
    if (this.mimeType == null) {
      this.mimeType = getMimeType(getContent());
    }
    return this.mimeType;
  }

  public byte[] getContent() throws IOException {
    if ((this.content == null) && (this.file != null) && (this.file.exists())) {
      InputStream in = null;
      ByteArrayOutputStream out = null;
      try
      {
        in = new FileInputStream(this.file);
        out = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
          out.write(ch);
        }
        this.content = out.toByteArray();
      } finally {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      }
    }
    return this.content;
  }
  public static String getMimeType(byte[] bytes)
  {
    String suffix = getFileSuffix(bytes);
    String mimeType;
    if ("JPG".equals(suffix)) {
      mimeType = "image/jpeg";
    }
    else
    {
      if ("GIF".equals(suffix)) {
        mimeType = "image/gif";
      }
      else
      {
        if ("PNG".equals(suffix)) {
          mimeType = "image/png";
        }
        else
        {
          if ("BMP".equals(suffix))
            mimeType = "image/bmp";
          else
            mimeType = "application/octet-stream"; 
        }
      }
    }
    return mimeType;
  }
  public static String getFileSuffix(byte[] bytes)
  {
    if ((bytes == null) || (bytes.length < 10)) {
      return null;
    }

    if ((bytes[0] == 71) && (bytes[1] == 73) && (bytes[2] == 70))
      return "GIF";
    if ((bytes[1] == 80) && (bytes[2] == 78) && (bytes[3] == 71))
      return "PNG";
    if ((bytes[6] == 74) && (bytes[7] == 70) && (bytes[8] == 73) && (bytes[9] == 70))
      return "JPG";
    if ((bytes[0] == 66) && (bytes[1] == 77)) {
      return "BMP";
    }
    return null;
  }
}