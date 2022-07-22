import java.util.*;

//A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
        //Every adjacent pair of words differs by a single letter.
        //Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
        //sk == endWord
        //Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
  //      Output: 5
    //    Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //convert list to hashset
        Set<String> dic = new HashSet<>(wordList);
        if(!dic.contains(endWord)) {
            return 0;
        }
        Queue<String> queue= new LinkedList<>();
        queue.offer(beginWord);
        int level=0;
        while (!queue.isEmpty()) {
            int size=queue.size();
            level++;
            if(queue.contains(endWord)) {
                return level;
            }
            for(int k=0;k<size;k++) {
                var cur=queue.poll();
                StringBuilder sb=new StringBuilder(cur);
                for (int i = 0; i < cur.length(); i++) {
                    for(char j='a';j<='z';j++) {
                        sb.setCharAt(i,j);
                        if(dic.contains(sb.toString())) {
                            queue.offer(sb.toString());
                            dic.remove(sb.toString());
                        }
                    }
                    sb.setCharAt(i,cur.charAt(i));
                }

            }
        }
        return 0;
    }
}
