/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author r0n
 */
class OrgModel {
    private String[] cmlArg;
    public boolean vflag=false;
           String apotelesma="";
           String username="";

    OrgModel(String[] args){
        cmlArg=args;
    }
   
    void checkArg() throws MalformedURLException, IOException {
        int k = 0, j=2;
        String arg;
        if (j == cmlArg.length && cmlArg[k].startsWith("-")) {
            arg = cmlArg[k++];
            if (arg.equals("-u")) {
                arg = cmlArg[k++];
                username=arg;  
                vflag = true;
                codeRuning();
              }
          } 
     }

    private void codeRuning() throws MalformedURLException, IOException {
        URL u = new URL("http://search.twitter.com/");//to url toy search
        String host = u.getHost();
        int port = 80;//port
        String file = "/search.json?q=from%3A"+username+"&src=typd";//to search pou theloume gia to request sigkekrimenou user
        SocketAddress remote = new InetSocketAddress(host, port);//sindesh me search.twitter.com
        SocketChannel channel = SocketChannel.open(remote);//anigma channel sindesi client me web
        String request = "GET " + file + " HTTP/1.1\r\n" + "User-Agent: HTTPGrab\r\n" //send request ston server gia na paro to apotelesma tou search
                    + "Accept: text/*\r\n" + "Connection: close\r\n" + "Host: " + host + "\r\n" + "\r\n";
        ByteBuffer header = ByteBuffer.wrap(request.getBytes());
        channel.write(header);//apostoli ston server to request
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int i=0;
        String s="";
        while (channel.read(buffer) != -1) {//diavasma istera apo to request
            if(i==1){//kathara dedomena json
                buffer.flip();
                Charset charset = Charset.defaultCharset(); //metatropi to bytebuffer se charbuffer kai metgatropi sde string 
                CharsetDecoder decoder = charset.newDecoder();  
                CharBuffer charBuffer = decoder.decode(buffer);  
                s += charBuffer.toString();  
                buffer.clear();
            }
            if(i==0){ //stixia pou den xriazomaste
                buffer.flip();
                i=1;
            }
        }
        channel.close();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(s);
        JSONArray array=(JSONArray) jsonObject.get("results");//to apotelesma mpori nane ena i polla
        Iterator iterator = array.iterator();
        int n=1;
        while (iterator.hasNext()) {
            apotelesma+=("\n======Tweet \""+ n +"\"==========\n");
            apotelesma+=((JSONObject)iterator.next()).get("text").toString();
            n++;
        }
        if (n==1){ 
            apotelesma=("======No public Tweets======");
        }
    }
}
