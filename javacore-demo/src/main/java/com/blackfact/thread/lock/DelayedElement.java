package com.blackfact.thread.lock;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement implements Delayed {
    private final long deadlineMillis;

    public DelayedElement(long deadlineMillis) {
        this.deadlineMillis = deadlineMillis;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //计算剩余到期时间，并将剩余到期时间根据传入的时间单位进行换算
        return unit.convert(deadlineMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        //剩余到期时间少的，优先级更高
        long diff = (getDelay(TimeUnit.MILLISECONDS) -
                other.getDelay(TimeUnit.MILLISECONDS));
        return (diff == 0) ? 0 : ((diff < 0) ? -1 : 1);
    }
}