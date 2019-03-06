package cn.yan.study.springboot.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyMsg implements Serializable {
    private String sender;
    private String data;
    private Date senderTime;
}
