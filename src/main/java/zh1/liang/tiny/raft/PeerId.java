package zh1.liang.tiny.raft;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.Period;

/**
 * @author: zhe.liang
 * @create: 2024-01-09 21:00
 **/
@Slf4j
public class PeerId implements Serializable {

    public static final String IP_ANY = "0.0.0.0";

    public static PeerId ANY_PEER = new PeerId();

    private Endpoint endpoint = new Endpoint(IP_ANY,0);

    private String str;

    private int idx;

    private int priority = 0;

    public PeerId() {
    }

    public static PeerId emptyPeer(){
        return new PeerId();
    }

    public void setPriority(int priority){
        this.priority = priority;
        this.str = null;
    }

}
