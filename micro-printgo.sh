#!/bin/bash
thumucchay=/opt/crm
thamsogrep="/opt/crm/"

# cac gam mau cho text
r=`tput setaf 1`
r1=`tput setaf 2`
N=`tput sgr0`

start(){
    t1=`ps -ef | grep Run | grep -v grep | grep -v run | wc -l`
    if [ $t1 -eq 0  ];
    then
    $thumucchay/run-api.sh & 
    echo "Start${r1}[ok]${N}"
    fi
    if [ $t1 -eq 1  ]; then
    echo "${r1}Started${N}"
    fi
    if (( $t1 > 1  ))
    then
    echo "${r1}Started${N} hon mot tien trinh"
    fi
}

stop(){
#/bin/ps -ef |grep $thamsogrep |grep -v grep |awk '{print$2}' |xargs kill -9 >/dev/null 2>&1 &
#killall java
PID=`ps aux | grep java | grep Run | awk '{print $2}'`
if [ $PID > 0 ]; then
echo "$PID"
kill $PID 
echo "${r1}MPSGW STOPED.${N}"
else
echo "${r1}MPSGW NOT RUNNING.${N}"
fi 

#echo "kill -9 ${r1}[ok]${N}"
}

restart(){
    stop
	sleep 5
    start
}

case "$1" in
  start)
   start 
   ;;
  stop)
   stop 
   ;;
  status)
    t3=`ps -ef |grep Run |grep -v grep |grep -v run |wc -l`
	PID=`ps aux | grep java | grep MPSGW_BYS | awk '{print $2}'`
    if [ $t3 -eq 1 ];
    then
    echo "${r1}MPSGW PROCESS RUNNING...PID $PID ${N}"
    fi
    if [ $t3 -eq 0 ];
    then
    echo "${r1}MPSGW NOT PROCESS RUNNING.${N}"
    fi
    if (( $t3 > 1  ))
    then
    echo $thumucchay "dang chay nhieu hon 1 tien trinh, phai dung /run-api.sh kill de kill -9 ung dung nay"
    fi
    ;;

  restart)
   restart  
   ;;

  *)
    echo $"Usage: $0 {start|stop|restart|status}"
    exit 1
    ;;	
esac
#exit $?
