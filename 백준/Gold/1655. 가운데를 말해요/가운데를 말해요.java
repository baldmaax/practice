import java.io.*;
import java.util.*;

public class Main {
    
    // 표준 입출력
    private static BufferedReader br;
    private static BufferedWriter bw;
    
    // 가운데를 말하기 위해 필요한 두 개의 우선순위 큐
    // 최대 힙은 작은 값을, 최소 힙은 큰 값을 저장한다
    private static PriorityQueue<Integer> minHeap; // 오름차순 우선순위 큐
    private static PriorityQueue<Integer> maxHeap; // 내림차순 우선순위 큐
    
    public static void main(String[] args) throws IOException {
        // 입출력 스트림 초기화
        initIOStream();
        // 우선순위 큐 초기화
        initQueues();
        
        // 입력 받을 숫자의 개수 읽기
        int N = readInputNumber();
        
        // 입력받은 숫자들을 가운데를 말하기 위한 규칙에 따라 적절한 큐에 추가
        for (int i = 0; i < N; i++) {
            addNumberToAppropriateQueue(readInputNumber());
            balanceQueues();
            printMedian();  
        }
        
        // 입출력 스트림 종료
        closeIOStream();
    }
    
    // 입출력 스트림 초기화 함수
    private static void initIOStream() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }
    
    // 우선순위 큐 초기화 함수
    private static void initQueues() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }
    
    // 입력 받은 숫자를 읽는 함수
    private static int readInputNumber() throws IOException {
        return Integer.parseInt(br.readLine());
    }
    
    // 숫자를 적절한 큐에 추가하는 함수
    // 규칙: 두 큐의 크기가 같다면 maxHeap에 추가, 그렇지 않다면 minHeap에 추가
    private static void addNumberToAppropriateQueue(int number) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.offer(number);
        }
        minHeap.offer(number);
    }
    
    // 두 큐를 균형있게 만드는 함수
    // 규칙: maxHeap의 루트 값이 minHeap의 루트 값보다 크다면, 두 값을 교환
    private static void balanceQueues() {
        if (!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
            int tmp = minHeap.poll();
            minHeap.offer(maxHeap.poll());
            maxHeap.offer(tmp);
        }
    }
    
    // 중간 값을 출력하는 함수
    // 규칙: maxHeap의 루트 값이 항상 중간 값
    private static void printMedian() throws IOException {
        bw.write(String.valueOf(maxHeap.peek()));
        bw.newLine();
    }
    
    // 입출력 스트림 종료 함수
    private static void closeIOStream() throws IOException {
        br.close();
        bw.flush();
        bw.close();
    }
}