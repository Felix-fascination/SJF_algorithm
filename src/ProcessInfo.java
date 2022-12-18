
public class ProcessInfo implements Comparable<ProcessInfo> {
    private final int processIndex;
    private final int processSize;
    private final int workStartingTime;
    private final int endingTime;

    public ProcessInfo(int processIndex, int processSize, int endingTime) {
        this.processIndex = processIndex;
        this.processSize = processSize;
        this.workStartingTime = endingTime - processSize;
        this.endingTime = endingTime;
    }

    @Override
    public String toString() {
        return "- Процесс " + processIndex
                + " (размер " + processSize
                + ") начал свою работу в "
                + workStartingTime
                + " и закончил работу в "+ endingTime;
    }

    @Override
    public int compareTo(ProcessInfo o) {
        ProcessInfo processInfo = (ProcessInfo) o;
        return workStartingTime - processInfo.workStartingTime;
    }
}
