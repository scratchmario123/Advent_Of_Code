package adventofcode2023.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AplentyPartTwo {
    static Map<String,String> workflowToConditions = new HashMap<>();
    final static Map<String,Integer> xmas = new HashMap<>(Map.ofEntries(Map.entry("x",0),Map.entry("m",2),Map.entry("a",4),Map.entry("s",6)));
    static List<Long> combinations = new ArrayList<>();

    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day19/TestData.txt"));
            int i = 0;
            while (!sl.get(i).isBlank()) {
                String workflow = sl.get(i).split("\\{")[0], condition = sl.get(i).split("\\{")[1].split("}")[0];
                workflowToConditions.put(workflow,condition);
                i++;
            }
            int[] values = new int[]{1,4000,1,4000,1,4000,1,4000};
            addCombinations(values,0,"in");
            long sum = 0;
            for (long c : combinations) sum += c;
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static void addCombinations(int[] values, int condIndex, String toGo) {
        if (toGo.equals("R")) return;
        else if (toGo.equals("A")) {
            combinations.add(((long) (values[1] - values[0] + 1) *(values[3]-values[2]+1)*(values[5]-values[4]+1)*(values[7]-values[6]+1)));
            return;
        }
        int[] newValues = Arrays.copyOf(values,8);
        String cond = workflowToConditions.get(toGo).split(",")[condIndex];
        if (cond.contains("<")) {
            int index = xmas.get(cond.split("<")[0]), condNum = Integer.parseInt(cond.split("<")[1].split(":")[0]);
            if (values[index+1] > condNum) {
                newValues[index] = condNum;
                addCombinations(newValues,condIndex+1,toGo);
                int[] newValues2 = Arrays.copyOf(values,8);
                newValues2[index+1] = condNum-1;
                addCombinations(newValues2,0,cond.split(":")[1]);
            } else if (values[index+1] < condNum) {
                addCombinations(newValues,0,cond.split(":")[1]);
            } else {
                addCombinations(newValues,condIndex+1,toGo);
            }
        } else if (cond.contains(">")) {
            int index = xmas.get(cond.split(">")[0]), condNum = Integer.parseInt(cond.split(">")[1].split(":")[0]);
            if (values[index] < condNum) {
                newValues[index+1] = condNum;
                addCombinations(newValues,condIndex+1,toGo);
                int[] newValues2 = Arrays.copyOf(values,8);
                newValues2[index] = condNum+1;
                addCombinations(newValues2,0,cond.split(":")[1]);
            } else if (values[index] > condNum) {
                addCombinations(newValues,0,cond.split(":")[1]);
            } else {
                addCombinations(newValues,condIndex+1,toGo);
            }
        } else {
            addCombinations(values,0,cond);
        }
    }
}
