package demo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import demo.data.HeartbeatRequest;
import demo.data.HeartbeatResponse;
import demo.data.LogEntry;
import demo.scheduler.Scheduler;
import demo.scheduler.TimerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhe.liang
 * @create: 2024-01-05 14:29
 *
 * 先对日志达成共识，再应用指令
 **/
@Slf4j
public class NodeImpl {

    @Getter
    private Integer totalMoney;

    private State state;

    List<Endpoint> endpoints = new ArrayList<>();


    private Endpoint master;

    private Endpoint self;


    private long lastIndex;

    private Scheduler heartbeatScheduler = new TimerManager(1);

    private int heartbeatDelayMs = 1000;


    //客户端的指令被这个方法处理
    public void onApply(Task task){
        if(this.state == State.STATE_MASTER){
            execute(task);
            //TODO Store to local
            sendCommandToSlave(task);
        }
    }

    private void execute(Task task){


    }

    private void sendCommandToSlave(Task task){
        this.lastIndex++;
        for (int i = 0; i < endpoints.size(); i++) {
            Endpoint endpoint = endpoints.get(i);
            if(endpoint.equals(self)){
                continue;
            }
            String ip = endpoint.toString();
            LogEntry logEntry = new LogEntry(lastIndex, task);
            doSendCommandToSlave(logEntry,ip);
        }

    }

    private void startHeartbeatTimer(){
        this.heartbeatScheduler.schedule(() -> sendHeartbeat(), 1000 , TimeUnit.MILLISECONDS);
    }

    private void sendHeartbeat() {
        for (int i = 0; i < endpoints.size(); i++) {
            Endpoint endpoint = endpoints.get(i);
            if(endpoint.equals(self)){
                continue;
            }
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setEndpoint(endpoint);
            heartbeatRequest.setMaster(master);
            heartbeatRequest.setCommittedIndex(lastIndex);
        }
    }

    public void onHeartbeatReturned(HeartbeatResponse response){

        if(!response.isSuccess()){
            log.error("从节点失败");
        }
        startHeartbeatTimer();
    }

    public HeartbeatResponse handleHeartbeatRequest(HeartbeatRequest request){
        Endpoint remoteMaster = request.getMaster();
        if(remoteMaster.equals(master)){
            //返回失败
            return null;
        }

        Long committedIndex = request.getCommittedIndex();
        //应用状态机
        //返回成功

        return null;


    }

    //发送给从节点
    private void doSendCommandToSlave(LogEntry log, String ip) {

    }

    //从节点接收主节点传递过来的指令
    private Response acceptCommand(LogEntry log){
        if(this.state != State.STATE_SLAVE){
            return null;
        }
        if(log.getIndex() != lastIndex + 1){
            //拉取之前的
            return new Response();
        }
        lastIndex = log.getIndex();
        execute(log.getTask());
        //success
        return new Response();
    }

    public void start(){

    }
}
