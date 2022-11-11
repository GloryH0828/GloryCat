import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GloryH
 */
public class Request {
    /**
     * Request 输入流
     */
    private final InputStream inputStream;

    private String method;
    private String uri;
    private String httpVersion;
    private Map<String,String> queries;
    private Map<String,String> headers;

    //TODO:读取body

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void read(){
        byte[] bytes = new byte[1024];
        try {
            int read = inputStream.read(bytes);
            String req = new String(bytes);
            System.out.println(req);
            // 切分 请求信息
            String[] split = req.split("\r\n");
            // 第一行 method uri http-version
            String[] first = split[0].split(" ");
            this.method = first[0];
            this.uri = first[1];
            this.httpVersion = first[2];
            // 获取 query 参数
            this.queries = parseQueries();
            //直至第一个空白行 header 参数
            this.headers = new HashMap<>(1);
            int index = 1;
            for (int i = 1; i < split.length; i++) {
                if(split[i].length() == 0){
                    index = i;
                    break;
                }
                String[] keyValue = split[i].split(":");
                headers.put(keyValue[0].toLowerCase(),keyValue[1]);
            }
            // TODO:获取直至结束行 Body
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> parseQueries() {
        Map<String,String> queries = new HashMap<>(1);
        if(!this.uri.contains("?")){
            return queries;
        }
        String queryString = this.uri.split("\\?")[1];
        String[] queryList = queryString.split("&");
        for (String query : queryList) {
            String[] split = query.split("=");
            if(split.length == 2){
                queries.put(split[0], split[1]);
            }else{
                queries.put(split[0],"");
            }
        }
        return queries;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

}
