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

        int[][] adjacentList = new int[numberOfNode][numberOfNode];
        int[][] adjacentList2 = new int[numberOfNode][numberOfNode];

        for(int i=0;i<numberOfLine;i++){
            st = new StringTokenizer(br.readLine());

            int leftNode = Integer.parseInt(st.nextToken()) - 1;
            int rightNode = Integer.parseInt(st.nextToken()) - 1;

            adjacentList[leftNode][rightNode] = 1;
            adjacentList[rightNode][leftNode] = 1;

            adjacentList2[leftNode][rightNode] = 1;
            adjacentList2[rightNode][leftNode] = 1;
        }


        boolean[] visited = new boolean[numberOfNode];
        DFS2(numberOfNode, adjacentList, visited, startNode-1);
        bw.write("\n");
        BFS2(numberOfNode, adjacentList2, startNode-1);
        bw.write("\n");

        bw.flush();
        bw.close();

    }

    private static void DFS2(int numberOfNode, int[][] adjacentList,  boolean[] visited, int targetNode) throws IOException {

        bw.write((targetNode+1) + " ");
        visited[targetNode] = true;

        for(int i = 0; i < numberOfNode ; i++){
            if(adjacentList[targetNode][i] == 1 && !visited[i]){
                adjacentList[targetNode][i] = -1;
                adjacentList[i][targetNode] = -1;

                DFS2(numberOfNode, adjacentList, visited, i);
            }
        }

    }

    private static void BFS2(int numberOfNode, int[][] adjacentList, int startNode) throws IOException {

        //시작 지점을 찾는다
        List<Integer> checkList = new LinkedList<>();
        boolean[] visited = new boolean[numberOfNode];
        checkList.add(startNode);
        visited[startNode] = true;

        //0이 아니면 표시한다 검사할 list에 넣는다
        do{
            int targetNode = checkList.get(0);
            checkList.remove(0);

            bw.write((targetNode+1) + " ");

            for(int i = 0; i < numberOfNode ; i++){
                if(adjacentList[targetNode][i] == 1 && !visited[i]){
                    checkList.add(i);
                    visited[i] = true;
                    adjacentList[targetNode][i] = -1;
                    adjacentList[i][targetNode] = -1;
                }
            }
        }while(!checkList.isEmpty());

    }
}
