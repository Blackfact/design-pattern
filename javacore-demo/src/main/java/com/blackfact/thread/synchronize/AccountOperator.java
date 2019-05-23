package com.blackfact.thread.synchronize;

/**
 *  * 账户操作类
 *  
 */
class AccountOperator implements Runnable {
    private Account account;

    public AccountOperator(Account account) {
        this.account = account;
    }

    public void run() {
        // 在AccountOperator 类中的run方法里，我们用synchronized 给account对象加了锁。
        // 这时，当一个线程访问account对象时，其他试图访问account对象的线程将会阻塞，直到该线程访问account对象结束。
        // 也就是说谁拿到那个锁谁就可以运行它所控制的那段代码。
        synchronized (account) {
            account.deposit(500);
            account.withdraw(500);
            System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
        }
    }
}