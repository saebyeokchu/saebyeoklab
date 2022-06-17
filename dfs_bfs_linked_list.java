import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numberOfNode = Integer.parseInt(st.nextToken());
        int numberOfLine = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        LinkedList<Integer> a1[] = new LinkedList[numberOfNode];
        LinkedList<Integer> a2[] = new LinkedList[numberOfNode];

        for (int i = 0; i < numberOfNode; i++) {
            a1[i] = new LinkedList();
            a2[i] = new LinkedList();
        }


        for (int i = 0; i < numberOfLine; i++) {
            st = new StringTokenizer(br.readLine());

            int leftNode = Integer.parseInt(st.nextToken()) - 1;
            int rightNode = Integer.parseInt(st.nextToken()) - 1;

            a1[leftNode].addLast(rightNode);
            a1[rightNode].addLast(leftNode);

            a2[leftNode].addLast(rightNode);
            a2[rightNode].addLast(leftNode);
        }

        Arrays.stream(a1).forEach(list -> Collections.sort(list));
        Arrays.stream(a2).forEach(list -> Collections.sort(list));

        boolean[] visited = new boolean[a1.length];

        DFS(a1, visited, startNode - 1);
        bw.write("\n");
        BFS(a2, startNode - 1);

        bw.flush();
        bw.close();
    }

    private static void DFS(LinkedList<Integer> adjacentList[], boolean[] visited, int targetNode) throws IOException {

        LinkedList<Integer> targetList = adjacentList[targetNode];

        bw.write((targetNode + 1) + " ");
        visited[targetNode] = true;

        while (!targetList.isEmpty()) {
            int target = targetList.pop();
            if (!visited[target]) {
                visited[target] = true;
                DFS(adjacentList, visited, target);
            }
        }


    }

    private static void BFS(LinkedList<Integer> adjacentList[], int startNode) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjacentList.length];

        queue.add(startNode);

        while (!queue.isEmpty()) {
            int targetNode = queue.poll();
            LinkedList<Integer> targetList = adjacentList[targetNode];

            bw.write((targetNode + 1) + " ");
            visited[targetNode] = true;

            while (!targetList.isEmpty()) {
                int target = targetList.pop();
                if (!visited[target]) {
                    queue.add(target);
                    visited[target] = true;
                }
            }


        }
    }

}
