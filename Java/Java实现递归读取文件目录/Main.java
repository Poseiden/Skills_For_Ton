package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input your directory which will recurrence:");
        String root = input.next();
        File file = new File("E:\\abcd.txt");
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file,false),"utf-8");
            recurrence(root,writer);       //递归调用
//            writer.write(stringBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void recurrence(String root,Writer writer) throws IOException {
        File file = new File(root);
        File[] files = file.listFiles();
        if (files != null && files.length != 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    recurrence(f.getAbsolutePath(), writer);
                } else {
//                    stringBuilder.append(f.getAbsolutePath()+"\n");
//                    System.out.println(f.getAbsolutePath());
                      writer.write(f.getAbsolutePath()+"\n");
                      writer.flush();
                }
            }
        }
    }
}
