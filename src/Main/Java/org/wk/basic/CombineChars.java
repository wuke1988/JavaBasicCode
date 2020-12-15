package org.wk.basic;

import java.util.*;
import java.util.stream.Collectors;

public class CombineChars {
    private  static List<CharCount> list= null;
    public static void main(String[] args) {
        CombineChars obj = new CombineChars();
        System.out.println(obj.combineChars("axayaxyz", 2));
    }

    public static class CharCount implements Comparable<CharCount> {
        private char chracter;
        private int count;

        public CharCount(char chracter, int count) {
            this.chracter = chracter;
            this.count = count;
        }

        public char getChracter() {
            return chracter;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(CharCount o2) {
            return o2.getCount() - this.getCount();
        }
    }

    public String combineChars(String input, int interval) {
        list = buildList(input);
        StringBuilder builder = new StringBuilder();
        while (!list.isEmpty()) {
            String str = getStr(interval);
            if(str!=null){
                builder.append(str);
            }
            else {
                return "";
            }
        }
        return builder.toString();
    }

    private List<CharCount> buildList(String input) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (map.containsKey(input.charAt(i))) {
                int count = map.get(input.charAt(i)) + 1;
                map.put(input.charAt(i), count);
            } else {
                map.put(input.charAt(i), 1);
            }
        }
        List<CharCount> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            list.add(new CharCount(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    /**
     * 对list重新排序；取前interval个count最大的key,加入String。然后将对应key的count-1;
     *
     * @param interval
     * @return
     */
    private String getStr(int interval) {
        Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < interval; i++) {
            if (i < list.size()) {
                builder.append(list.get(i).getChracter());
                list.get(i).setCount(list.get(i).getCount() - 1);
            }else {
                if(i>list.size()){
                    return null;
                }
            }
        }
        list = list.stream().filter(model -> model.getCount() > 0).collect(Collectors.toList());
        return builder.toString();
    }
}
