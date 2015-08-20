# Graceful shutdown for spark streaming

This is based on the StatefulNetworkWordCount Spark example and shows a
A simple class to implement graceful shutdown, in particular saving RDDs before the application
stops. A typical use-case is version upgrades: a long-running streaming application goes down
for a version upgrade, but the state (from `updateStateByKey`) should not be lost. This class
registers DStreams and won't call `ssc.stop` until all streams have finished saving their RDDs

All parts of the application need to check the `shuttingDown` flag and decide if they
should continue working or not. For instance, state updates in `updateStateByKey`
probably won't make sense, since the state saved on disk is what will be used on restart.

Speaking of restart, the application is responsible for loading the saved RDD and using
it as `initialRDD` in `updateStateByKey`.