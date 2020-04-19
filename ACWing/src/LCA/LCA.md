````
树是一种无向图
任意两个顶点之间存在唯一一条路径

树中可以指定一个特殊的节点作为根节点
一个有根的树叫做有根树
反之无根树

有根树可以分层,我们定义一个点的深度为它到根节点的距离

一条边的两个端点,靠近根的节点叫做另一个节点的父节点

沿着父节点一直到根的所有节点叫做这个点的祖先
(父节点也可以看做祖先)

````
###最近公共祖先
````
给出一棵有根树的不同2个节点u和v
找到一个离根最远的节点x
使得x同时是u和v的祖先
x就是u和v的LCA(Lowest common ancestor)

````
####暴力做法
````
先从u往根节点遍历,经过的点都打上标记
再从v往根节点遍历,路上碰到第一个打上标记的点就是它们的LCA

时间复杂度一次询问是O(n)   n位树的节点个数
最差情况,树为链表,根为链表中间元素
u和v为链表两端点,则要遍历整棵树
````
####倍增算法
```
记录f[u]为u的父节点,即u向根走一步能到达的节点
对于根节点f[root]=0

倍增:记录up[u][k]为从u向根走2^k步到达的节点
显然 up[u][0]=fa[u]
走一步,走到父节点
则:
up[u][k]=up[up[u][k-1]][k-1]
2^(k-1) + 2^(k-1) = 2^k


```
