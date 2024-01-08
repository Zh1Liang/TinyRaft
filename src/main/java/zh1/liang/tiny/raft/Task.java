package zh1.liang.tiny.raft;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhe.liang
 * @create: 2024-01-05 14:27
 **/
@Data
public class Task implements Serializable {
    private byte command;
    private Integer money;


}
