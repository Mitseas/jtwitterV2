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
class OrgController {
    
    private OrgModel o_model;
    private OrgView  o_view;
    
    OrgController(OrgModel model, OrgView view) throws MalformedURLException, IOException {
        o_model=model;
        o_view=view;
        o_model.checkArg();
        o_view.showString(o_model.vflag, o_model.apotelesma);
    }
    
    void transferCmlArgToModel(){
    }
    
}
