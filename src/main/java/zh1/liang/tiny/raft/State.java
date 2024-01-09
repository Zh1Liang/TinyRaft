package zh1.liang.tiny.raft;

public enum State {
    STATE_LEADER,
    STATE_CANDIDATE,
    STATE_FOLLOWER,
    STATE_ERROR,
    STATE_UNINITIALIZED,
    STATE_SHUTTING,
    STATE_SHUTDOWN,
    ;

    public boolean isActive(){
        return this.ordinal() < STATE_ERROR.ordinal();
    }
}
