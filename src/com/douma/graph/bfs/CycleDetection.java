package com.douma.graph.bfs;

import com.douma.graph.AdjSet;
import com.douma.graph.Graph;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class CycleDetection {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;

    private boolean hasCycle = false;

    public CycleDetection(Graph g) {
        this.g = g;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean bfs(int v) {
        if (g == null) return false;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // 维护顶点的前一个顶点
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 维护顶点的前一个顶点
                    prevs[w] = curr;
                } else {
                    if (w == prevs[curr]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        CycleDetection graphBFS = new CycleDetection(g);
        System.out.println(graphBFS.hasCycle());
    }
}
