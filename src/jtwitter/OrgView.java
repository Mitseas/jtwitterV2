/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitter;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author r0n
 */
class OrgView {
    
    private OrgModel o_model;
  
    /**Constructor**/
    OrgView(OrgModel model) {
        o_model=model;
    }
    
    void showString(boolean b,String apotelesma) throws UnsupportedEncodingException{
        if(b){
            System.out.println("======USER "+o_model.username+"==========");
            PrintStream ps = new PrintStream(System.out, true, "iso-8859-7");//http://msdn.microsoft.com/en-us/library/windows/desktop/dd317756%28v=vs.85%29.aspx
            ps.println(apotelesma);
        }else{
            System.out.println("---no or missing or wrong parameters given----");
        }
    }
  
}
