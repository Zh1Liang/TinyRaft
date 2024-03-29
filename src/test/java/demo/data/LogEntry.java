package demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import demo.Task;

import java.io.Serializable;

/**
 * @author: zhe.liang
 * @create: 2024-01-08 13:44
 **/
@Data
@AllArgsConstructor
public class LogEntry implements Serializable {
    //日志索引
    private long index;

    //封装的指令信息
    private Task task;
}
