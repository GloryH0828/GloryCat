import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author GloryH
 */
public class HttpServer {
    /**
     * 端口，默认为 80
     */
   private static final int PORT = 80;

    /**
     * 接受请求
     */
   public void receiving(){
       try {
           //创建 Socket 服务
           ServerSocket serverSocket = new ServerSocket(PORT);
           // 循环接收请求
           while (true){
               Socket socket = serverSocket.accept();
               // 获取链接对象的输入流
               InputStream inputStream = socket.getInputStream();
               // 创建 Request
               Request request = new Request(inputStream);
               // 读取请求
               request.read();
               //若资源路径存在，返回对应处理结果
               //若资源路径不存在，返回404

           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
