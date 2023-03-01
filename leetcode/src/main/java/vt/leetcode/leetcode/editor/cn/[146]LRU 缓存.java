package vt.leetcode.leetcode.editor.cn;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class LruCache {

    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        // 最近 最少使用 要记录频率，根据频率排序
        // 使用懒加载技术，在插入时，才删除
        LinkedHashMap<Integer, Integer> cache;
        int capacity = 16;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new LinkedHashMap<>(this.capacity);
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Integer val = cache.get(key);
            makeRecently(key, val);
            return val;
        }

        private void makeRecently(int key, int val) {
            cache.remove(key);
            cache.put(key, val);
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                makeRecently(key, value);
                return;
            }
            if (cache.size() >= capacity) {
                deleteOld();
            }
            cache.put(key, value);
        }

        private void deleteOld() {
            Map.Entry<Integer, Integer> next = cache.entrySet().iterator().next();
            if (next != null) {
                cache.remove(next.getKey());
            }
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
