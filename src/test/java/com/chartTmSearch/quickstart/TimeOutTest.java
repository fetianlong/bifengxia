package com.chartTmSearch.quickstart;/**
 * 包名 com.chartTmSearch.quickstart
 * 版权：Copyright tiens. All Rights Reserved.
 * 描述详情：
 * 创建者： pjh.
 * 创建时间：2017/9/12-11:45.
 * <p>
 * 修改者：pjh.
 * 修改时间：2017/9/12-11:45.
 * 修改原因：
 * 修改内容：
 */

import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 *
 * @auther pjh
 * @create 2017/9/12
 */
public class TimeOutTest {
    public static void main(String[] args) {
        final Timer timer = new HashedWheelTimer();
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("timer 5 S out");
            }
        }, 5, TimeUnit.SECONDS);

        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("timer 10 S out...");
            }
        }, 10, TimeUnit.SECONDS);

        timer.stop();
    }
}
