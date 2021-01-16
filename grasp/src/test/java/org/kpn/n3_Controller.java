package org.kpn;

import org.junit.jupiter.api.DisplayName;

@DisplayName("GRASP: controller")
public class n3_Controller {

    public static class Controller{

        public void handle(String message){

            switch (message){
                case "msg0":
                    /* do sth*/
                    break;
                case "msg1":
                    /* do sth*/
                    break;
                default:
                    /* do sth*/
                    break;
            }
        }
    }
}
