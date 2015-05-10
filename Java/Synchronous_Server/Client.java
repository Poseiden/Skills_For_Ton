package com.company;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Tony on 2015/5/9.
 */
public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(int serverPort){
        try{
            //To specified ip address send request
            socket = new Socket("192.168.142.138",serverPort);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            while(true){
                //to construct a input stream by keyboard
                BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
                String message = bReader.readLine();
                writer.println(message);
                System.out.println("发送字符串成功！");
                writer.flush();
                if("bye".equals(message)){
                    break;
                }
                String received=reader.readLine();
                System.out.println("收到返回字符串" + received);
            }
            writer.close();
            reader.close();
            socket.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

}
