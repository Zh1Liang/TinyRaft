package demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import demo.Endpoint;

/**
 * @author: zhe.liang
 * @create: 2024-01-08 19:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeartbeatRequest {

    //主节点应用的最新的日志索引
    private Long committedIndex;

    //当前节点的IP地址
    private Endpoint endpoint;

    //主节点的IP地址
    //如果不一致，说明可能发生了网络分区
    private Endpoint master;
}
