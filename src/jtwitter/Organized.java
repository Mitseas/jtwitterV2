/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitter;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author r0n
 */
public class Organized {
    public String args;
    //... Create model, view, and controller.  They are
    //    created once here and passed to the parts that
    //    need them so there is only one copy of each.
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        OrgModel      model      = new OrgModel(args);
        OrgView       view       = new OrgView(model);
        OrgController controller = new OrgController(model, view);
    
    }
}