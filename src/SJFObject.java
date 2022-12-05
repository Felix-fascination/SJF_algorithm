import java.util.*;

public class SJFObject {
    private static final int MAX_MACHINE_TIME = 1000;
    private HashMap<Integer, Map.Entry<Integer, Integer>> inputTimesProcessSize;
    private int machineTime = MAX_MACHINE_TIME;
    private int index = 0;

    public SJFObject() {
        inputTimesProcessSize = new HashMap<>();
    }

    public void addElement(int inputTime, int processSize){
        inputTimesProcessSize.put(index, Map.entry(inputTime, processSize) );
        machineTime = Math.min(inputTime, machineTime);
        index++;
    }

    public int[] nextProcess(){
        ArrayList<Map.Entry<Integer, Map.Entry<Integer, Integer>>> entries = new ArrayList<>();
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
        return new int[]{ machineTime, indexToDelete, machineTime - minSize - inputTime + 1 };
    }
}
