#!/bin/bash -x

 isInteger()
 {
     re='^[0-9]+$'
     if ! [[ $1 =~ $re ]] ; then
         echo "error: Not a number $1"
         exit 1
     fi
 }
 # adding
 isVal()
 {
     if [[ $OPTARG == -* ]]; then
         echo 'Option '$1' requires an argument'
         exit 1
     fi
 }

 # Retrieving user parameters

 while :; do
 while getopts ":u:r:l:s:" opt; do

     case $opt in
     u)
         users=$OPTARG
         isVal $opt
         isInteger ${users}

      ;;
     r)
         rampup=$OPTARG
         isVal $opt
         isInteger ${rampup}
    ;;
    l)
         loop=$OPTARG
         isVal $opt
         isInteger ${loop}
     ;;
     s)
         simulation=$OPTARG
         isVal $opt
     ;;
     :)
         echo "Option -$OPTARG requires an argument."
         exit 1
     ;;
     *)
         echo 'unknown options: $OPTARG'
     ;;
     esac
     done
     echo $OPTIND
     ((OPTIND++))
     [ $OPTIND -gt $# ] && break
    done

 # $ENV is passed from Jenkins, indicating the running environment.
 # The following constructs a list of options for this $ENV
 # specified in ./envConf.json

 getEnvConf()
 {
      envConfFile=envConfig.json
      if [ ! -f "$envConfFile" ]; then
      echo "file not found"
      else
      export PYTHONIOENCODING=utf8
      eval "$3=`cat $envConfFile | python -c 'import json,sys;obj=json.load(sys.stdin);print obj[0]["environments"]["'$1'"][0].get("'$2'","")'`"
      fi
 }

     getEnvConf $ENV baseUrl baseUrl

 export GATLING_CONF="`pwd`/src/test/resources"
 export JAVA_OPTS="-DuserCount=${users:-2} -DrampUpSeconds=${rampup:-1} -DloopRounds=${loop:-1} -DbaseUrl=${baseUrl}"

 /opt/gatling-charts-highcharts-bundle-2.2.2/bin/gatling.sh \
      -nr \
      -sf `pwd`/src/test/scala/ \
      -s Simulations.${simulation} \
      -bf `pwd`/target \
      -df `pwd`/src/test/resources/data \
      -rf ${PERF_RESULTS}
