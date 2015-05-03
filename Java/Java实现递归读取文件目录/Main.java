package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input your directory which will recurrence:");
        String root = input.next();
        StringBuffer stringBuilder = new StringBuffer();
        recurrence(root,stringBuilder);       //递归调用
//        File file = new File("E:\\abcd.txt");
//        Writer writer = null;
//        try {
//            writer = new OutputStreamWriter(new FileOutputStream(file,false),"gb2312");
//            writer.write(stringBuilder.toString());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private static void recurrence(String root,StringBuffer stringBuilder) {
        File file = new File(root);
        File[] files = file.listFiles();
        if (files != null && files.length != 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    recurrence(f.getAbsolutePath(), stringBuilder);
                } else {
//                    stringBuilder.append(f.getAbsolutePath()+"\n");
                    System.out.println(f.getAbsolutePath());
                }
            }
        }
    }
}
