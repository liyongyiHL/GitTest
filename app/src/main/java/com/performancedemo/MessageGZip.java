/*
 *
 * Copyright (c) 2015-2020  Bonree Company
 * 北京博睿宏远科技发展有限公司  版权所有 2015-2020
 *
 * PROPRIETARY RIGHTS of Bonree Company are involved in the
 * subject matter of this material.  All manufacturing, reproduction, use,
 * and sales rights pertaining to this subject matter are governed by the
 * license agreement.  The recipient of this software implicitly accepts
 * the terms of the license.
 * 本软件文档资料是博睿公司的资产,任何人士阅读和使用本资料必须获得
 * 相应的书面授权,承担保密责任和接受相应的法律约束.
 *
 */
package com.performancedemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MessageGZip {
	public static final int BUFFER = 1024;  
    public static final String EXT = ".gz";  

    /** 
     * compress data
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */ 
    public static byte[] compress(byte[] data) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(data);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        // 压缩  
        compress(bais, baos);  
        byte[] output = baos.toByteArray();  
        baos.flush();  
        baos.close();  
        bais.close();  
        return output;  
    }  


    /** 
     * compress data
     *  
     * @param is 
     * @param os 
     * @throws Exception 
     */  
    public static void compress(InputStream is, OutputStream os) throws Exception {  
        GZIPOutputStream gos = new GZIPOutputStream(os);  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = is.read(data, 0, BUFFER)) != -1) {  
            gos.write(data, 0, count);  
        }  
          
        gos.flush();  
        gos.close();  
    }  


    /** 
     * decompress data
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decompress(byte[] data) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        decompress(bais, baos);  
        data = baos.toByteArray();  
        baos.flush();  
        baos.close();  
        bais.close();  
        return data;  
    }

    /** 
     * decompress data
     *  
     * @param is 
     * @param os 
     * @throws Exception 
     */  
    public static void decompress(InputStream is, OutputStream os)  
            throws Exception {  
        GZIPInputStream gis = new GZIPInputStream(is);  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = gis.read(data, 0, BUFFER)) != -1) {  
            os.write(data, 0, count);  
        }  
        gis.close();
    }  
}
