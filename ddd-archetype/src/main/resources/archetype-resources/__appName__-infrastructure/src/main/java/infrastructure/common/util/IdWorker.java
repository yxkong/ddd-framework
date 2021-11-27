#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.util;

import java.util.List;

/**
 * 分布式Id生成器，做到了相对有序
 * @author ${author}
 * @Description: Base64编码解码
 */
public class IdWorker {

    // ==============================Fields===========================================
    /**
     * 开始时间截 (2021-11-11)，设置一个时间戳会让id值从比较小的值开始
     */
    private final long twepoch = 1636617732604L;

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 内存自增序列占4位，最大可以999，毫秒级999能满足99.9999%的需求了
     */
    private long maxSequence = 999L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    private long len_critical_2 = 10L;
    private long len_critical_3 = 100L;
    private long len_critical_4 = 1000L;
    private String fill_1_zero = "0";
    private String fill_2_zero = "00";
    private String fill_3_zero = "000";



    // ==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @throws Exception
     */
    public IdWorker() throws Exception {
        // 获取当前服务器的机器id start
        String cluster = System.getProperty("cluster");
        String[] strings = cluster.split(",");
        String ip = "";
        ip = WebUtil.getLocalHostAddress();
        int index = 0;
        for (int j = 0; j < strings.length; j++) {
            if (ip.equals(strings[j])) {
                index = j + 1;
            }
        }
        // 获取当前服务器的机器id end

        this.workerId = index;
    }

    /**
     * 构造函数
     *
     * @throws Exception
     */
    public IdWorker(List<String> cluster, int skip) throws Exception {

        String ip =  WebUtil.getLocalHostAddress();
        int index = 0;
        for (int j = 0; j < cluster.size(); j++) {
            if (ip.equals(cluster.get(j))) {
                index = j + skip;
            }
        }
        // 获取当前服务器的机器id end

        this.workerId = index;
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *   （当前时间戳-开始时间戳，17位）+3位的机器位+3位的内存序列
     *   <p>ps:17位是在2299年前，如果超过了2299年就会溢出到18位</p>
     *   <p>算出来的id，在单台机器内相对有序，如果一毫米秒整体不超过100条数据</p>
     * @return SnowflakeId
     */
    public synchronized long nextId() {

        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        StringBuilder builder = new StringBuilder();

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = sequence + 1;
            if (sequence > maxSequence) {
//                System.out.println("溢出："+sequence);
                // 毫秒内序列溢出,重置为0 ，等下一毫秒
                sequence = 0;
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else { // 时间戳改变，毫秒内序列重置
//            System.out.println("自然增长："+sequence);
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        builder.append(timestamp - twepoch);

        // 不用String.format("%03d", workerId) 是为了更好的性能
        if (workerId < len_critical_2){
            builder.append(fill_2_zero).append(workerId);
        }else if(workerId< len_critical_3){
            builder.append(fill_1_zero).append(workerId);
        }else if (workerId<len_critical_4){
            builder.append(workerId);
        }

        // 不用String.format("%03d", sequence) 是为了更好的性能
        if (sequence < len_critical_2) {
            builder.append(fill_2_zero).append(sequence);
        } else if (sequence < len_critical_3) {
            builder.append(fill_1_zero).append(sequence);
        } else if (sequence < len_critical_4) {
            builder.append(sequence);
        }
        return Long.parseLong(builder.toString());
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    // ==============================test=============================================

    /**
     * 测试
     */
//    public static void main(String[] args) {
//
//        IdWorker idWorker = null;
//        try {
//            idWorker = new IdWorker();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 10000; i++) {
//            //正常执的业务应该超过System.out.println的执行时间，在使用
//            long id = idWorker.nextId();
//            System.out.println(id);
//        }
//    }


}
