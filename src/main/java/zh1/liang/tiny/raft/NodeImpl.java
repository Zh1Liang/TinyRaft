package zh1.liang.tiny.raft;

import java.io.Serializable;

/**
*
* @author: zhe.liang
*
* @create: 2024-01-09 20:57
**/
public class NodeImpl implements Serializable {


    private String groupId;

    private volatile State state;

    private PeerId leaderId= new PeerId();

    private final PeerId selfId;

    //集群当前的配置信息
    private ConfigurationEntry configurationEntry;

    private String ip = "test";
    private int port;
    private String str;







}
