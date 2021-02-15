#Data Replication In Distributed System

##Why Data Replication (Data Redundancy)
1. Higher availability
2. Reduced latency. (ex. CDN, replicated data closer to user)
3. Read scalability.


###Data replication approaches
<p>Replication is easy when the data on the node is static (doens't change during replication). 
So when we talk the approches of replication we need to know what kind of data we are going 
to replica </p>

#### Common Approaches
1. Single leader.
2. Multiple leaders.
3. Leaderless.

Each approach copies data either synchronously or asynchronously.

##Single Leader Replication (Master-Slave Pattern)
<p>It's the most common approach, when a new write comes to the master, it writes the data to local storage
and sends the same data to its replicas. Postgres, MySQL, MongoDB, Kafka use this mode of replication.
</p>

####Synchronous vs Asynchronous
#####Synchronous
<p>when a write request comes to master, master sends it to replicas and wait for the acknowledgment 
from each replica, once the master received all acknowledgment then notify the client.</p>

###### Pros:
* Data consistency.
* Easy to upgrade a slave to a nwe master. (Since all slaves have same data as master)

###### Cons:
* Increased latency.
* Reduced availability. (the master might be blocked by an unavailable slave)

Due to the impact on latency and availability the synchronous replication is rarely used.

####Booting up a new slave node
<p>A node can come and go any time in the distributed system, when one node goes down, then we might
require to boot up a new node, this new node would not have any data to start with, we can copy data from
master, how to copy the data:</p>

1. Take a snapshot of data without the lock-on database in master, and then send it to the new node.
2. Then the new node requests the master to send all changes that occurred since the snapshot was taken.
3. Then the new node merge all changes and start to follow master. 

####Promoting a new master
1. How to detect a leader has failed?
2. How to promote a new leader?

###How Does Replication works
There are three ways to replicate data from master to slave.
1. Statement-based replication.
2. Write-Ahead log.
3. Row-based replication.
#####Statement-based Replication
Master logs every statements and then sends it to slaves, and slaves execute statements on its node.
1. If a statement contains non-deterministic functions like RAND() or Now()?
2. An update depends on previous insert? (race condition)
#####Write-Ahead Log(WAL)
When a query comes to master, it is written in an append-only log file(WAL file), WAL describes the 
data on a very low level, it's quite coupled with engine system.
1. It's impossible upgrade software version on master and slaves separately.
Postgres uses this strategy.

#####Row-based replication
When a query comes to master, master sends a record that contains all news values of columns in the level 
of row.
Mysql uses this strategy.

<u>
https://medium.com/@sandeep4.verma/data-replication-in-distributed-systems-part-1-13f52410faa3
</u>