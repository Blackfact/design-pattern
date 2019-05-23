package com.blackfact.io.buffered;

import java.io.*;

public class CopyFileUtil {
    public static void main(String[] args) {
        String filePath1 = "1.jpg";
        String filePath2 = "2.jpg";
        File file1 = new File("1.jpg");
        File file2 = new File("2.jpg");
        copyFile(file1,file2);
    }

    public static void copyFile(File oldFile,File newFile){
        InputStream is = null;
        BufferedInputStream bis = null;

        OutputStream os = null;
        BufferedOutputStream bos = null;
        try{
            is = new FileInputStream(oldFile);
            bis = new BufferedInputStream(is);

            os = new FileOutputStream(newFile);
            bos = new BufferedOutputStream(os);

            byte[] b = new byte[1024]; // 代表一次最多读取1KB的内容
            int length = 0;
            while ((length = bis.read(b)) != -1){
                // length代表实际读取的字节数
                bos.write(b,0,length);
            }
            bos.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bos !=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 观察源码发现buffered的处理流的close方法调用了io的close，所以没必要再写，只需要调用外层流的close
            // 一般关闭，先关闭外层流，再关闭内层流。即先打开后关闭，后打开的先关闭；依赖的先关，被依赖的后关闭
//            if( is !=null){
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if(os != null){
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }
}
