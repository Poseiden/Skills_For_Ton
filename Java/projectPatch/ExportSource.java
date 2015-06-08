package com.ton;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tony-linux on 6/4/15.
 */
public class ExportSource {

    public static List<String> parseConfig(File configFile,int op)throws IOException{
        List<String> pathList = new ArrayList<String>();

        BufferedReader bReader = new BufferedReader(new FileReader(configFile));
        String line = null;
        String filePath = null;
        do{
            line = bReader.readLine();
            if(line == null || line.trim().length() == 0){
                break;
            }

            int _index = line.indexOf("#");
            if(_index==0){
                continue;
            }else if(_index >0){
                filePath = line.substring(0,_index).trim();
            }else if(_index == -1){
                filePath = line.trim();
            }
            if(filePath.indexOf("\\")!= -1){
                filePath = filePath.replace("\\", "/");
            }

            if(op == 1){
                if(filePath.indexOf("src/")==0){
                    filePath = filePath.replaceFirst("src/","WEB-INF/classes/");
                }
                if(filePath.indexOf(".java")== filePath.length()-4){
                    filePath = filePath.replace(".java",".class");
                }
                if(filePath.indexOf("Web-Content/")==0){
                    filePath = filePath.replace("Web-Content/","");
                }
                if(filePath.indexOf("web/")==0){
                    filePath = filePath.replace("web/","");
                }
            }
            pathList.add(filePath);
        }while(true);

        return pathList;
    }

    public static Map<String,Integer> exportJavaFile(String projectPath,List<String> pathList)throws IOException{
        Map<String,Integer> countMap = new HashMap<String,Integer>();

        DateFormat df = new SimpleDateFormat("yyyyMMdd_HH");
        File patchDirectory = new File("patch_"+df.format(Calendar.getInstance().getTime())+"_"+projectPath);
        if(!patchDirectory.exists()){
            patchDirectory.mkdirs();
        }

        FileInputStream fis = null;
        DataInputStream dis= null;
        FileOutputStream fos =null;
        DataOutputStream dos  = null;

        for(String filePath:pathList){

            File outputDir = new File(patchDirectory+"/"+filePath.substring(0,filePath.lastIndexOf("/")));
            if(!outputDir.exists()){
                outputDir.mkdirs();
            }

            fis = new FileInputStream(projectPath+"/"+filePath);
            dis = new DataInputStream(fis);

            fos = new FileOutputStream("patch_"+df.format(Calendar.getInstance().getTime())+"_"+projectPath+"/"+filePath);
            dos = new DataOutputStream(fos);

            int temp;
            while((temp = dis.read()) != -1){
                dos.write(temp);
                dos.flush();
            }
            dis.close();
            dos.close();

            System.out.println(filePath+"\t\tdone!");
            String clazzType = filePath.substring(filePath.lastIndexOf(".")+1);
            if(countMap.containsKey(clazzType)){
                countMap.put(clazzType,countMap.get(clazzType)+1);
            }else{
                countMap.put(clazzType,1);
            }
        }

        return countMap;
    }
}
