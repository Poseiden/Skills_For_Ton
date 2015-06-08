package com.ton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by tony-linux on 6/6/15.
 */
public class Main {
    public static void main(String[] args)throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println(System.getProperty("user.dir"));
        do {
            System.out.println("Please choose config file type:");
            System.out.println("1.XML");
            System.out.println("2.TXT");
            int configType = input.nextInt();

            System.out.println("Please input your config file path(absolute path):");
            String fileName = input.next();
            File configFile = new File(fileName);

            System.out.println("Please input your project path:");
            String projectPath = input.next();

            System.out.println("Please choose operation:");
            System.out.println("1.Export java file");
            System.out.println("2.Expot class file");
            int op = input.nextInt();

            List<String> pathList = null;
            try {
                if (op == 1) {
                    pathList = ExportSource.parseConfig(configFile, 0);
                } else if (op == 2) {
                    pathList = ExportSource.parseConfig(configFile, 1);
                }
            }catch(FileNotFoundException e){
                System.out.println(configFile.getName()+" not found!!");
                System.out.println("Continue?(y/others)");
                String _con = input.next();
                if(_con.toLowerCase().equals("y")){
                    continue;
                }else {
                    break;
                }
            } catch(IOException ioe){
                System.out.println("file stream exception!!");
                System.out.println("Continue?(y/others)");
                String _con = input.next();
                if(_con.toLowerCase().equals("y")){
                    continue;
                }else {
                    break;
                }
            }

            try {
                Map<String, Integer> countMap = ExportSource.exportJavaFile(projectPath, pathList);

                System.out.println("**************************************");
                for (Map.Entry<String, Integer> _entry : countMap.entrySet()) {
                    System.out.println(_entry.getKey() + ":" + _entry.getValue());
                }
                System.out.println("**************************************");
            }catch(FileNotFoundException e){
                System.out.println(e.getMessage());
                System.out.println("Continue?(y/others)");
                String _con = input.next();
                if(_con.toLowerCase().equals("y")){
                    continue;
                }else {
                    break;
                }
            }catch(IOException e){
                System.out.println("file stream exception");
                System.out.println("Continue?(y/others)");
                String _con = input.next();
                if(_con.toLowerCase().equals("y")){
                    continue;
                }else {
                    break;
                }
            }
//        System.out.println(System.getProperty("user.dir"));der(new FileInputStream(configFile)));
            System.out.println("All things have done ! Continue?(y/others)");
            String _con = input.next();
            if(_con.toLowerCase().equals("y")){
                continue;
            }else {
                break;
            }
        }while(true);
    }
}
