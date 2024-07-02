import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingFeeCalculator {

    // 停车计费规则
    private static final int FREE_DURATION_MINUTES = 15;
    private static final int FREE_DURATION_SECONDS = FREE_DURATION_MINUTES * 60;
    private static final int MAX_DAILY_FEE = 3000; // 30元对应3000分
    private static final int CHARGE_RATE_PER_15_MINUTES = 500; // 每15分钟5元对应500分

    public static void main(String[] args) {
        // 示例入场和离场时间
        String inTime = "2024-07-01 10:30:00";
        String outTime = "2024-07-01 12:30:00";

        // 计算停车费用
        int fee = calculateParkingFee(inTime, outTime);
        System.out.println("停车费用为：" + fee / 100.0 + " 元"); // 将分转换为元输出
    }

    public static int calculateParkingFee(String inTime, String outTime) {
        // 将入场时间和离场时间解析为 LocalDateTime
        LocalDateTime inDateTime = parseDateTime(inTime);
        LocalDateTime outDateTime = parseDateTime(outTime);

        // 计算停车时长，单位为秒
        long durationSeconds = calculateDurationSeconds(inDateTime, outDateTime);

        // 判断是否在免费时间段内
        if (isWithinFreePeriod(inDateTime, outDateTime)) {
            return 0; // 免费
        }

        // 计算费用
        int totalFee = calculateFee(durationSeconds);

        // 如果超过一天，按照最高封顶费用计算
        totalFee = Math.min(totalFee, MAX_DAILY_FEE);

        return totalFee;
    }

    // 解析时间字符串为 LocalDateTime
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    // 计算停车时长，单位为秒
    private static long calculateDurationSeconds(LocalDateTime inDateTime, LocalDateTime outDateTime) {
        return java.time.Duration.between(inDateTime, outDateTime).getSeconds();
    }

    // 判断是否在免费时间段内
    private static boolean isWithinFreePeriod(LocalDateTime inDateTime, LocalDateTime outDateTime) {
        LocalDateTime startFreeTime = inDateTime.plusSeconds(FREE_DURATION_SECONDS);
        return outDateTime.isBefore(startFreeTime);
    }

    // 计算停车费用
    private static int calculateFee(long durationSeconds) {
        // 计算费用，按照每15分钟5元的标准计费
        int numberOfPeriods = (int) Math.ceil((double) durationSeconds / (15 * 60)); // 每15分钟为一个单位
        return numberOfPeriods * CHARGE_RATE_PER_15_MINUTES;
    }
}
