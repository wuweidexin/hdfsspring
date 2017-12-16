#!/bin/bash
homePath=`pwd`
cd $homePath
startStop=$1
function start(){
	#svn up
	echo "git pull"
	git pull
	echo "gradle build"
    gradle build
    echo "rm jetty webapps hdfsspring.war"	
	rm -f /chen/jetty-8.1.21/webapps/hdfsspring.war
	echo "cp hdfsspring.war to jetty webapps "
	cp $homePath/build/libs/hdfsspring.war /chen/jetty-8.1.21/webapps/
	echo "start jetty"
	sh /chen/jetty-8.1.21/bin/jetty.sh start
}
function stop(){
    sh /chen/jetty-8.1.21/bin/jetty.sh stop
}
case $startStop in
	(start)
		start
	;;
	
	(stop)
		stop
		;;
	
	(restart)
		stop
		start
		;;
	
	(*)
		echo "update.sh start|stop|restart"
		exit 1
		;;
esac

sleep 1
