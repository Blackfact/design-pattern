**ReentrantLock:**

可重入性描述这样的一个问题：一个线程在持有一个锁的时候，它内部能否再次（多次）申请该锁。如果一个线程已经获得了锁，其内部还可以多次申请该锁成功。那么我们就称该锁为可重入锁.
2.1 Lock接口说明

Modifier and Type	Method	Description
void	lock()	获取锁
void	lockInterruptibly()	除非当前线程被中断，否则获取锁定
Condition	newCondition()	返回绑定到此Lock实例的新Condition实例
boolean	tryLock()	只有在调用时它是空闲的才能获取锁
boolean	tryLock(long time, TimeUnit unit)	如果在给定的等待时间内空闲并且当前线程未被中断，则获取锁
void	unlock()	释放锁

**ReadWriteLock：**
为了提高性能，Java 提供了读写锁，在读的地方使用读锁，在写的地方使用写锁，灵活控制，
如果没有写锁的情况下，读是无阻塞的,在一定程度上提高了程序的执行效率。
读写锁分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，这是由 jvm 自己控制的，你只要上好相应的锁即可。 
读锁：
如果你的代码只读数据，可以很多人同时读，但不能同时写，那就上读锁 
写锁：
如果你的代码修改数据，只能有一个人在写，且不能同时读取，那就上写锁。总之，读的时候上读锁，写的时候上写锁！ 
Java  中  读  写  锁  有  个  接  口      java.util.concurrent.locks.ReadWriteLock ， 
也有具体的实现ReentrantReadWriteLock。

**Semaphore:**
又叫信号量，是专门用于只允许N个任务同时访问某个共享资源的场景。
一些常用的方法：
Semaphore semaphore = new Semaphore(10); //创建信号量，并为该信号量指定初始许可数量
semaphore.acquire(); //获取单个许可，如果无可用许可前将一直阻塞等待
semaphore.acquire(2); //获取指定数目的许可，如果无可用许可前将会一直阻塞等待
semaphore.tryAcquire(); //尝试获取单个许可，如果无可用许可直接返回false，不会阻塞
semaphore.tryAcquire(2); //尝试获取指定数量的许可，如果无可用许可直接返回false，不会阻塞
semaphore.tryAcquire(2, 2, TimeUnit.SECONDS); //在指定的时间内尝试获取指定数量的许可，如果在指定的时间内获取成功，返回true，否则返回false。
semaphore.release(); //释放单个许可
semaphore.release(2); //释放指定数量的许可
int availablePermits = semaphore.availablePermits(); //查询信号量当前可用许可数量

需要特别注意的是，信号量并没有严格要求释放的许可数量必须与已申请的许可数量一致。
也就是说多次调用release方法，会使信号量的许可数增加，达到动态扩展许可的效果。
例如：初始permits 为1，调用了两次release()，availablePermits会改变为2，这在应对某些需要动态提升并发任务量的需求时特别有用。

**AtomicInteger:**

AtomicInteger、AtomicLong、AtomicDouble等都是对基本类型的Atomic封装，为基本类型封装线程安全的访问方式。
特别适用于需要多线程同时操作一个数值变量，完成累积计数统计等操作的场景。

它们的底层实现原理都基于如下两点：

1、使用volatile关键字保证多线程间对数值更新的实时可见性。

2、使用CAS操作保证操作的原子性。CAS操作会在对值进行更新前，检查该值是否是之前读取到的旧值，
如果是，则说明该值目前还没有其他线程更新，不存在并发冲突，可以安全设置为新值，整个检查+设置新值的操作通常由CPU提供单指令完成，
保证原子性。显然，CAS可能会失败（遇到并发冲突时），则自动重新读取当前值，不断循环，直至CAS成功。

AtomicInteger典型操作如下：

AtomicInteger atomicInteger = new AtomicInteger(1);
atomicInteger.compareAndSet(1, 2); //CAS操作，如果当前值等于期望值，则将当前值设置为新值，并返回true表示设置成功，否则返回false表示设置失败。该操作是原子性的。

atomicInteger.getAndIncrement(); //当前值加1，同时返回加1前的旧值
atomicInteger.incrementAndGet(); //当前值加1，同时返回加1后的新值

atomicInteger.getAndDecrement(); //当前值减1，同时返回减1前的旧值
atomicInteger.decrementAndGet(); //当前值减1，同时返回减1后的新值

atomicInteger.getAndAdd(3); //当前值加3，同时返回加3前的旧值
atomicInteger.addAndGet(3); //当前值加3，同时返回加3后的新值

由于Atomic采用无锁化设计，在高并发场景下通常拥有较好的性能表现。性能是ReentantLock的好几倍

**可重入锁：**

也叫递归锁，指在同一个线程可以反复获取锁多次，然后需要释放多次。JAVA环境下ReentrantLock和Synchronized都是可重入锁。

**公平锁：**
加锁前检查是否有排队等待的线程，优先排队等待的线程，先来先得。

**非公平锁：**
加锁时不考虑排队等待问题，直接尝试获取锁，获取不到自动到队尾等待 

1. 非公平锁性能比公平锁高 5~10 倍，因为公平锁需要在多核的情况下维护一个队列 
2. Java 中的 synchronized 是非公平锁，ReentrantLock 默认的 lock()方法采用的是非公平锁

**CountDownLatch**

CountDownLatch可以设置一个初始计数，一个线程可以调用await等待计数归零。
其他线程可以调用countDown来减小计数。

计数不可被重置，CountDownLatch被设计为只触发一次。

CountDownLatch countDownLatch = new CountDownLatch(5); //初始化CountDownLatch并设置初始计数值
countDownLatch.countDown(); //将计数值-1
countDownLatch.await(); //等待直至计数值为0
countDownLatch.await(2, TimeUnit.MINUTES); //等待直至计数值为0，或者超时时间达到

我们常常会将一个任务拆分为可独立运行的N个任务，待N个任务都完成后，再继续执行后续任务。这便是CountDownLatch的典型应用场景。

CountDownLatch典型用法1：某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为n new CountDownLatch(n) ，每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。

CountDownLatch典型用法2：实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，将其计数器初始化为1，多个线程在开始执行任务前首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。

**CyclicBarrier**

CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会打开，届时所有被屏障拦截的线程同时开始继续执行。

CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量。每个线程通过调用CyclicBarrier的await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞，直至所有要拦截的线程都调用了CyclicBarrier的await后，大家同时解锁。

CyclicBarrier还提供另外一个构造函数CyclicBarrier(int parties, Runnable barrierAction)，用于在屏障打开前，先执行barrierAction，方便处理更复杂的业务场景。

**BlockingQueue**

队列是解决线程间通信的利器，几乎绝大部分使用wait、notify这类底层线程间通信的编程风格都可以重构为更简单的队列模型，线程间的协作问题可以通过队列中的消息传递来解决。

BlockingQueue（有界阻塞队列）便是最常用的一种队列。
1.   ArrayBlockingQueue ：由数组结构组成的有界阻塞队列。 
2.   LinkedBlockingQueue ：由链表结构组成的有界阻塞队列。 
3.   PriorityBlockingQueue ：支持优先级排序的无界阻塞队列。

BlockingQueue的典型操作如下：

BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100); //初始队列并设置队列最大长度为100
blockingQueue.put("message"); //往队尾插入新消息，如果队列已满将一直等待队列有可用空间为止
blockingQueue.offer("message"); //往队尾插入新消息，如果队列已满导致无法插入，则直接返回false表示插入失败；如果队列未满，可以成功插入，则返回true表示插入成功
blockingQueue.offer("message", 2, TimeUnit.MINUTES); //普通offer的增强版，可以指定超时时间，如果无法插入先尝试等待指定超时时间，超时时间达到后还无法插入，直接返回false表示插入失败；超时时间达到前可以插入，则成功插入并返回true

String message = blockingQueue.take(); //从队头取出最新消息，如果队列为空，没有最新消息，则一直等待直到有最新消息为止
message = blockingQueue.poll(); //从队头取出最新消息，如果队列为空，没有最新消息，则直接返回null
message = blockingQueue.poll(2, TimeUnit.MINUTES); //普通poll的增强版，可以指定超时时间，如果没有最新消息先尝试等待指定超时时间。如果超时时间到达前有最新消息，则立即取出最新消息；如果超时时间达到后仍没有最新消息，则立即返回null

**PriorityBlockingQueue**

PriorityBlockingQueue（优先级队列）作为阻塞队列的一种特殊形态，是一个带优先级排序功能的无界阻塞队列。

PriorityBlockingQueue的典型操作与BlockingQueue基本一致。除了实现BlockingQueue的基本功能以外，PriorityBlockingQueue额外保证每次从对头取出的元素总是队列中优先级最高的元素。

由于需要比较队列中元素的优先级，所以加入队列的元素必须实现Comparable接口，或者在构建时指定实现了Comparator接口的比较器。两个元素将通过compareTo方法进行比较，小的元素的优先级高。

与BlockingQueue不同的是，PriorityBlockingQueue是一个无界队列，构造PriorityBlockingQueue时可以指定初始容量，但这并不意味着PriorityBlockingQueue是有界的，它会在队列满时自动扩容。所以需要特别注意由于控制逻辑不严谨导致内存溢出的风险。

另外，使用PriorityBlockingQueue的迭代器遍历队列时，你会发现队列元素是乱序的（与插入顺序不同）。事实上PriorityBlockingQueue只保证依次从队头取出元素是按照优先级排序的（参考最小堆的实现）；队列也不保证两个相同优先级元素的顺序，他们可能以任意顺序返回。

**DelayQueue：**
DelayQueue（延时队列）可以认为是PriorityBlockingQueue+元素必须实现Delayed接口的特定组合。

DelayQueue也是一个带优先级功能的无界阻塞队列，典型操作也同PriorityBlockingQueue基本一致。只不过它对优先级的定义整合了延迟场景的特定抽象。队列里存放实现了Delayed接口的元素。Delayed元素实现了getDelay方法，用于获取剩余到期时间，实现了CompareTo方法，用于按照到期时间排序，以便确定优先级。只有存在到期的元素时，才能从DelayQueue中提取元素，该队列的头部是到期最久的元素。

应用场景：https://www.cnblogs.com/shamo89/p/7055039.html

**CountDownLatch 和 CyclicBarrier区别：**

1.CountDownLatch 和 CyclicBarrier 都能够实现线程之间的等待，只不过它们侧重点不 同；
 CountDownLatch 一般用于某个线程  A 等待若干个其他线程执行完任务之后，它才执行；
 而 CyclicBarrier 一般用于一组线程互相等待至某个状态，然后这一组线程再同时 执行；
2.CountDownLatch 是不能够重用的，而  CyclicBarrier 是可以重用的。