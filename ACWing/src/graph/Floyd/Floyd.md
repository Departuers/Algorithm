###Floyd
````
1. 多源多汇最短路 Floyd  O(n^3)
2. 传递闭包
3. 最小环对于正权图,找边权和最小的环  
和spfa不同的是,spfa判断的是负环
4. 找恰好经过k条边的最短路
(倍增)

Floyd做法
if i!=j d[i][j]=正无穷 
if i==j d[i][j]=0

for k=1~n{
    for i=1~n{
       for j=1~n{
      d[i,j]=min( d[i,j] , d[i,k]+d[k,j] )      
      } 
   }
} 

dp分析:

状态表示: f[k,i,j] 所有从i点出发,最终走到j,
且中间只经过节点编号不超过k的所有路径

属性:路径长度的min

状态计算:状态划分:考虑last, 判断k这个点,
划分成2部分:所有路径包含节点k的路径,使得i走到k,k走到j路径最小
i~k取最小且k~j取最小:d[k-1][i][k]+d[k-1][k][j]
所有路径不包含节点k的路径:d[k-1][i][j]
直接去掉最高维,代码等价
d[i,j]=min( d[i,j] , d[i,k]+d[k,j] )      


传递闭包
给定一个有向图,无向图也可以看做有向图
a可以到b,b可以到c,
那么给a到c连一条边
所有能间接到达的点,都给它们连一条直接的边!
所有这样的边都连上,就成为原图的传递闭包
Floyd可以用O(n^3)求出传递闭包,邻接矩阵
1.初始化d(i,j)=g(i,j)

g(i,j){
   存在i->j这条边 1
   不存在i->j这条边 0
}
2.Floyd
for k   
    for i
        for j
            if( d(i,k)=1 & d(k,j)=1)
                d(i,j)=1
如果i能到k,k能到j,说明i能到j,那么把d(i,j)置为1
说明g(i,j)可以间接到达,连一条边

