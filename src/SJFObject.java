import java.util.*;

public class SJFObject {
    private static final int MAX_MACHINE_TIME = 1000;
    private final ArrayList<int[]> inputTimesProcessSize;
    private int machineTime = MAX_MACHINE_TIME;
    private int index = 0;

    public SJFObject() {
        inputTimesProcessSize = new ArrayList<>();
    }

    public void addElement(int inputTime, int processSize){
        inputTimesProcessSize.add(new int[]{index, inputTime, processSize});
        machineTime = Math.min(inputTime, machineTime);
        index++;
    }

    public int[] nextProcess(){
        ArrayList<int[]> entries = new ArrayList<>();
        for (int[] entry: inputTimesProcessSize){
            if(entry[1] <= machineTime) entries.add(entry);
        }
        if (entries.isEmpty()){
            machineTime++;
            return nextProcess();
        }
        int minSizeProcess = entries.get(0)[2];
        int flag = minSizeProcess;
        int indexToDelete = entries.get(0)[0];
        int inputTime = entries.get(0)[1];
        for (int[] entry: entries){
            minSizeProcess = Math.min(entry[2], minSizeProcess);
            if (flag != minSizeProcess){
                indexToDelete = entry[0];
                inputTime = entry[1];
            }
        }
        machineTime = machineTime + minSizeProcess;
        inputTimesProcessSize.get(indexToDelete)[1] = MAX_MACHINE_TIME;
        return new int[]{indexToDelete, machineTime - minSizeProcess - inputTime, machineTime};
        /*//ArrayList<Map.Entry<Integer, Map.Entry<Integer,   Integer>>> entries = new ArrayList<>();
        for (Map.Entry<Integer, Map.Entry<Integer, Integer>> entry: inputTimesProcessSize.entrySet()){
            if(entry.getValue().getKey() <= machineTime) entries.add(entry);
        }
        int inputTime = entries.get(0).getValue().getKey();
        int indexToDelete = entries.get(0).getKey();
        int minSize = entries.get(0).getValue().getValue();
        int flag = minSize;

        for (Map.Entry<Integer, Map.Entry<Integer, Integer>> entry: entries){
            minSize = Math.min(entry.getValue().getValue(), minSize);
            if (minSize != flag) {
                inputTime = entry.getValue().getKey();
                indexToDelete = entry.getKey();
            }
            flag = minSize;
        }
        inputTimesProcessSize.remove(indexToDelete);
        machineTime = machineTime + minSize;
        return new int[]{ machineTime, indexToDelete, machineTime - minSize - inputTime + 1 };*/
    }
}
