###A*
```
A*可以处理任意边权,但不能有负环,需要保证一定有解!
启发式搜索,搜索空间很大,使用估价函数可以舍去很多,
无解情况会把搜索空间搜索一遍,不如BFS,因为pq是O(log n) 
普通队列是O(1)
队列换成使用小根堆,
第一个存,从起点到当前点的真实距离,
第二个,从当前点到终点的估计距离
优先队列按照,起点到当前点的真实距离+当前点到终点的估计距离从小到大排序,
while(!q.impty()){
    t ->取出优先队列,队头

   if 终点第一次出队的时候 break

    优先队列每次挑选预测距离最小的点扩展
    for : t的邻边
       将邻边入队,能更新才入队
}
特别像Dijkstra,把估价函数都取0,Dijkstra是一种特殊的A*算法
证明:使得估算距离<=真实距离

精髓:把队列换成pq,然后给每条路一个估计距离,选出我们认为最好的点来扩展
(起点到当前点的真实距离+当前点到终点的估计距离最小就是最好的点
)