package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tony-linux on 5/9/15.
 */
public class Server extends ServerSocket {
    public Server(int serverPort)throws IOException{
        super(serverPort);  //用指定的端口构造一个ServerSocket
        try{
            while(true){
                //监听端口，等待客户端进入
                Socket socket = accept();
                //将会话交给线程处理
                new ServerThread(socket);

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    //inner Class
    class ServerThread extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        //read to conversion
        public ServerThread(Socket socket) throws IOException{
            this.socket = socket;
            //To constructor in or out stream in this conversion
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"utf-8"));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            start();
        }

        public void run(){
            try{
                //Communicate with Client util 'bye' read
                while (true){
                    //To receive infomation client send by input stream
                    String line = in.readLine();
                    if("bye".equals(line)){
                        break;
                    }
                    System.out.print("Receiving information : "+line);
                    String msg = line+"was accepted by Synchronous Server !!";
                    out.print(msg);
                    out.flush();
                }
                out.close();
                in.close();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
