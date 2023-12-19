package adventofcode2023.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AplentyPartOne {
    static Map<String,String> workflowToConditions = new HashMap<>();
    final static Map<String,Integer> xmas = new HashMap<>(Map.ofEntries(Map.entry("x",0),Map.entry("m",1),Map.entry("a",2),Map.entry("s",3)));


    public static void main(String[] args) {
        try {
            List<String> sl = Files.readAllLines(Path.of("src/adventofcode2023/day19/TestData.txt"));
            int i = 0, sum = 0;
            while (!sl.get(i).isBlank()) {
                String workflow = sl.get(i).split("\\{")[0], condition = sl.get(i).split("\\{")[1].split("}")[0];
                workflowToConditions.put(workflow,condition);
                i++;
            }
            i++;
            while (i < sl.size()) {
                int[] values = new int[4]; //xmas
                String[] categories = sl.get(i).substring(1,sl.get(i).length()-1).split(",");
                for (int j = 0; j < 4; j++) values[j] = Integer.parseInt(categories[j].substring(2));
                if (!isRejected(values,0,"in")) {
                    sum  = sum + values[0] + values[1] + values[2] + values[3];
                }
                i++;
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("No such file found");
        }
    }

    static boolean isRejected(int[] values, int condIndex, String toGo) {
        if (toGo.equals("R")) return true;
        else if (toGo.equals("A")) return false;
        String cond = workflowToConditions.get(toGo).split(",")[condIndex];
        if (cond.contains("<")) {
            if (values[xmas.get(cond.split("<")[0])] < Integer.parseInt(cond.split("<")[1].split(":")[0])) return isRejected(values,0,cond.split(":")[1]);
            else return isRejected(values,condIndex+1,toGo);
        } else if (cond.contains(">")) {
            if (values[xmas.get(cond.split(">")[0])] > Integer.parseInt(cond.split(">")[1].split(":")[0])) return isRejected(values,0,cond.split(":")[1]);
            else return isRejected(values,condIndex+1,toGo);
        } else return isRejected(values,0,cond);
    }
}
